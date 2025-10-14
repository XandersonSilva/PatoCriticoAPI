package edu.xanderson.PatoCritico.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import edu.xanderson.PatoCritico.model.entitys.UsuarioConfirmacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.enums.UsuarioEstadoEnum;
import edu.xanderson.PatoCritico.model.mappers.UsuarioMapper;
import edu.xanderson.PatoCritico.model.repository.UsuarioConfirmacaoRepository;
import edu.xanderson.PatoCritico.model.repository.UsuarioRepository;
import edu.xanderson.PatoCritico.service.UsuarioConfirmacaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioConfirmacaoServiceImpl implements UsuarioConfirmacaoService{
    private final UsuarioAutenticadoImpl usuarioAutenticado;
    private final EmailServiceImpl emailService;

    private final UsuarioConfirmacaoRepository usuarioConfirmacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Value("${tempo.duaracao.validacao.minutos}")
    private int TEMPO_DE_EXPIRACAO_CODIGO_MINUTOS;

    @Override
    @Transactional
    public UUID registrarUsuarioRecem_Criado(UsuarioEntity usuario) {
        UsuarioConfirmacaoEntity cnfrm = usuarioConfirmacaoRepository.findByUsuarioId(usuario.getId());
        
        if (cnfrm != null) usuarioConfirmacaoRepository.delete(cnfrm);

        UsuarioConfirmacaoEntity confirmacao = new UsuarioConfirmacaoEntity();
        UUID codigo = UUID.randomUUID();
        confirmacao.setCode(codigo);
        confirmacao.setDataExpiracao(
            LocalDateTime.now().plusMinutes(TEMPO_DE_EXPIRACAO_CODIGO_MINUTOS)
            );
        confirmacao.setUsuario(usuario);

        usuarioConfirmacaoRepository.save(confirmacao);

        emailService.enviarCodigoValidacaoEmail(confirmacao, TEMPO_DE_EXPIRACAO_CODIGO_MINUTOS);

        return codigo;
    }

    @Override
    public ResUsuarioDTO ativarUsuario(UUID usuarioId) {
        UsuarioConfirmacaoEntity confirmacao = existeUsuario(usuarioId);
        UsuarioEntity usuario = usuarioRepository.getReferenceById(usuarioId);

        if (!(confirmacao.getDataExpiracao().isAfter(LocalDateTime.now()))){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Usuário não pendente!"
            );
        }
        if (!(usuario.getUsusarioEstado() == UsuarioEstadoEnum.PENDENTE)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Usuário não pendente!"
            );
        }

        usuario.setUsusarioEstado(UsuarioEstadoEnum.ATIVO);
        usuarioRepository.save(usuario);

        return usuarioMapper.toResDto(usuario);
    }

    @Override
    public ResUsuarioDTO inativarUsuario(UUID usuarioId) {
        usuarioAutenticado.verificaPapelAdmin();
        
        UsuarioEntity usuario = usuarioRepository.getReferenceById(usuarioId);

        if (!(usuario.getUsusarioEstado() == UsuarioEstadoEnum.ATIVO)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Usuário não ativo!"
            );
        }

        usuario.setUsusarioEstado(UsuarioEstadoEnum.INATIVO);
        usuarioRepository.save(usuario);
        
        return usuarioMapper.toResDto(usuario);
    }

    @Override
    public ResUsuarioDTO reativarUsuario(UUID usuarioId) {
        usuarioAutenticado.verificaPapelAdmin();
        
        
        UsuarioEntity usuario = usuarioRepository.getReferenceById(usuarioId);

        if (!(usuario.getUsusarioEstado() == UsuarioEstadoEnum.INATIVO)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Usuário não ativo!"
            );
        }

        usuario.setUsusarioEstado(UsuarioEstadoEnum.ATIVO);
        usuarioRepository.save(usuario);
        
        return usuarioMapper.toResDto(usuario);
    }

    private UsuarioConfirmacaoEntity existeUsuario(UUID usuarioId){
        if (usuarioId == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Id do usuário não informado!"
        );
        UsuarioConfirmacaoEntity confirmacao = usuarioConfirmacaoRepository.findByUsuarioId(usuarioId);
        if(confirmacao == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O id informado não corresponde a um estado de confirmação de usuário!"
                );
        }

        return confirmacao;
    }
}
