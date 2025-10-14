package edu.xanderson.PatoCritico.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.xanderson.PatoCritico.model.dtos.ReqAvaliacaoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResAvaliacaoDTO;
import edu.xanderson.PatoCritico.model.entitys.AvaliacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.JogoEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.mappers.AvaliacaoMapper;
import edu.xanderson.PatoCritico.model.repository.AvaliacaoRepository;
import edu.xanderson.PatoCritico.model.repository.JogoRepository;
import edu.xanderson.PatoCritico.service.AvaliacaoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliacaoServiceImpl implements AvaliacaoService{
    private final UsuarioAutenticadoImpl usuarioAutenticado;
    private final EmailServiceImpl emailService;

    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoMapper avaliacaoMapper;
    private final JogoRepository jogoRepository;


    @Override
    public ResAvaliacaoDTO salvarAvaliacao(ReqAvaliacaoDTO dto) {
        Optional<JogoEntity> jogo = jogoRepository.findById(dto.getJogo().getId());
        if (!jogo.isPresent()) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "O id informado não corresponde a um jogo do sistema!"
        );

        AvaliacaoEntity avaliacao = avaliacaoMapper.fromReqDto(dto);
        avaliacao.setAutor(usuarioAutenticado.usuarioLogado());
        avaliacaoRepository.save(avaliacao);

        return avaliacaoMapper.toResDto(avaliacao);
        
    }

    @Override
    public ResAvaliacaoDTO editarAvaliacao(ReqAvaliacaoDTO dto) {
        AvaliacaoEntity avaliacao = existeAvaliacao(dto.getId());
        UsuarioEntity user = usuarioAutenticado.usuarioLogado();
        if(user.getId() != avaliacao.getAutor().getId()) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Somente o autor pode editar as proprias avaliações!"
        );

        avaliacaoMapper.updateFromDto(dto, avaliacao);

        avaliacaoRepository.save(avaliacao);

        return avaliacaoMapper.toResDto(avaliacao);
        
    }
    
    @Override
    public ResAvaliacaoDTO excluirAvaliacao(UUID avaliacaoId) {
        AvaliacaoEntity avaliacao = existeAvaliacao(avaliacaoId);
        UsuarioEntity user = usuarioAutenticado.usuarioLogado();
        if(user.getId() != avaliacao.getAutor().getId()) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Somente o autor pode excluir as proprias avaliações!"
        );

        avaliacaoRepository.delete(avaliacao);

        return avaliacaoMapper.toResDto(avaliacao);
    }

    @Override
    public ResAvaliacaoDTO removerAvaliacaoImpropria(UUID avaliacaoId) {
        usuarioAutenticado.verificaPapelAdmin();

        AvaliacaoEntity avaliacao = existeAvaliacao(avaliacaoId);

        emailService.notificarAvaliacaoRemovida(avaliacao);
    
        avaliacaoRepository.delete(avaliacao);

        return avaliacaoMapper.toResDto(avaliacao);
    }

    private AvaliacaoEntity existeAvaliacao(UUID dto){
        if(dto == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "O id da avaliação não foi informada!"
        );
        AvaliacaoEntity avaliacao = avaliacaoRepository.findById(dto).orElseThrow(() ->
        new ResponseStatusException(
                                    HttpStatus.BAD_REQUEST,
                                    "Não há avaliação no banco com o id informado!"
                                ));
        return avaliacao;
    }

}
