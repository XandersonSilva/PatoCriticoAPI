package edu.xanderson.PatoCritico.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.xanderson.PatoCritico.model.dtos.ResJogoDTO;
import edu.xanderson.PatoCritico.model.entitys.JogoEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioJogoEntity;
import edu.xanderson.PatoCritico.model.enums.StatusJogoEnum;
import edu.xanderson.PatoCritico.model.mappers.JogoMapper;
import edu.xanderson.PatoCritico.model.repository.JogoRepository;
import edu.xanderson.PatoCritico.model.repository.UsuarioJogoRepository;
import edu.xanderson.PatoCritico.service.UsuarioJogoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioJogoServiceImpl implements UsuarioJogoService{
    private final UsuarioAutenticadoImpl usuarioAutenticado;

    private final UsuarioJogoRepository usuarioJogoRepository;
    private final JogoRepository jogoRepository;
    private final JogoMapper jogoMapper;

    @Override
    public ResJogoDTO tornarJogoDesejo(UUID jogoId) {
        JogoEntity jogo = existejogo(jogoId);
        UsuarioEntity usuario = usuarioAutenticado.usuarioLogado();
        UsuarioJogoEntity usuarioJogo = estadoEmUso(usuario, jogo, StatusJogoEnum.LISTA_DE_DESEJOS);

        usuarioJogo.setStatus(StatusJogoEnum.LISTA_DE_DESEJOS);
        usuarioJogoRepository.save(usuarioJogo);
        
        return jogoMapper.toResDTO(jogo);
    }

    @Override
    public ResJogoDTO tornarJogoJogando(UUID jogoId) {
        JogoEntity jogo = existejogo(jogoId);
        UsuarioEntity usuario = usuarioAutenticado.usuarioLogado();
        UsuarioJogoEntity usuarioJogo = estadoEmUso(usuario, jogo, StatusJogoEnum.JOGANDO);

        usuarioJogo.setStatus(StatusJogoEnum.JOGANDO);
        usuarioJogoRepository.save(usuarioJogo);
        
        return jogoMapper.toResDTO(jogo);
    }

    @Override
    public ResJogoDTO tornarJogoFinalizado(UUID jogoId) {
        JogoEntity jogo = existejogo(jogoId);
        UsuarioEntity usuario = usuarioAutenticado.usuarioLogado();
        UsuarioJogoEntity usuarioJogo = estadoEmUso(usuario, jogo, StatusJogoEnum.FINALIZADO);

        usuarioJogo.setStatus(StatusJogoEnum.FINALIZADO);
        usuarioJogoRepository.save(usuarioJogo);
        
        return jogoMapper.toResDTO(jogo);
    }

    @Override
    public ResJogoDTO removerJogoBibliotecaPessoal(UUID jogoId) {
        JogoEntity jogo = existejogo(jogoId);
        UsuarioEntity usuario = usuarioAutenticado.usuarioLogado();
        UsuarioJogoEntity usuarioJogo = usuarioJogoRepository.findByDonoIdAndJogoId(usuario.getId(), jogo.getId());

        usuarioJogoRepository.delete(usuarioJogo);
        
        return jogoMapper.toResDTO(jogo);
    }

    @Override
    @Transactional
    public List<ResJogoDTO> visualizarBibliotecaPessoal() {
        List<UsuarioJogoEntity> jogos_db = usuarioJogoRepository.findByDonoId(
            usuarioAutenticado.usuarioLogado().getId()
            );
        List<JogoEntity> jogosEntities = new ArrayList<>();
        if (jogos_db.size() > 0) {
            for (UsuarioJogoEntity jogo : jogos_db) {
                jogosEntities.add(jogo.getJogo());
            }
        }
        
        return jogoMapper.toDTOList(jogosEntities);

    }
    
    private JogoEntity existejogo(UUID jogoId){
        if (jogoId == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Id do usuário não informado!"
        );
        JogoEntity jogo = jogoRepository.findById(jogoId).orElseThrow(
            () -> new ResponseStatusException(
                       HttpStatus.BAD_REQUEST,
                       "O id informado não corresponde a um estado de confirmação de usuário!"
                   )
        );

        return jogo;
    }

    private UsuarioJogoEntity estadoEmUso(UsuarioEntity user, JogoEntity jogo, StatusJogoEnum estado){
        UsuarioJogoEntity usuarioJogo = usuarioJogoRepository.findByDonoIdAndJogoId(user.getId(), jogo.getId());

        if (usuarioJogo == null) {
            usuarioJogo = new UsuarioJogoEntity();
            usuarioJogo.setDono(user);
            usuarioJogo.setJogo(jogo);
        }

        if (usuarioJogo.getStatus() == estado) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "A relação com o jogo já obedece os termos solicitados!"
        );

        return usuarioJogo;
    }
}
