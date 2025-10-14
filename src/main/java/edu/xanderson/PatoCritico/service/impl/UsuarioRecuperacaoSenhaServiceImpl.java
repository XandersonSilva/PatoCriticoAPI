package edu.xanderson.PatoCritico.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioRecuperacaoSenhaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioRecuperacaoSenhaEntity;
import edu.xanderson.PatoCritico.model.mappers.UsuarioMapper;
import edu.xanderson.PatoCritico.model.repository.UsuarioRecuperacaoSenhaRepository;
import edu.xanderson.PatoCritico.model.repository.UsuarioRepository;
import edu.xanderson.PatoCritico.service.UsuarioRecuperacaoSenhaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioRecuperacaoSenhaServiceImpl implements UsuarioRecuperacaoSenhaService{
    private final AutenticacaoServiceImpl autenticacaoService;
    private final EmailServiceImpl emailServiceImpl;
    private final UsuarioRecuperacaoSenhaRepository usuarioRecuperacaoSenhaRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Value("${tempo.duaracao.validacao.minutos}")
    private long TEMPO_EXPIRACAO_MINUTOS;

    @Override
    @Transactional
    public void gerarLinkRecuperarSenha(String usuarioEmail) {
        UsuarioEntity usuario = (UsuarioEntity) usuarioRepository.findByEmail(usuarioEmail);
        if (usuario == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "O usuário informado não existe!"
        );
        UsuarioRecuperacaoSenhaEntity recuperacao = usuarioRecuperacaoSenhaRepository.findByUsuario(usuario);

        if (recuperacao != null) usuarioRecuperacaoSenhaRepository.delete(recuperacao);

        recuperacao = new UsuarioRecuperacaoSenhaEntity();

        recuperacao.setExpiracao(LocalDateTime.now().plusMinutes(TEMPO_EXPIRACAO_MINUTOS));
        recuperacao.setToken(UUID.randomUUID());
        recuperacao.setUsuario(usuario);

        usuarioRecuperacaoSenhaRepository.save(recuperacao);

        emailServiceImpl.enviarEmailRecuperarSenha(recuperacao);
    }

    @Override
    @Transactional
    public ResUsuarioDTO alterarSenhaUsuario(ReqUsuarioRecuperacaoSenhaDTO dto) {
        UsuarioRecuperacaoSenhaEntity recuperacao = usuarioRecuperacaoSenhaRepository.findByToken(dto.getToken_link());

        if (recuperacao == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Não há pedidos de recuperação com esse token!"
        );

        if (!(recuperacao.getExpiracao().isAfter(LocalDateTime.now()))) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Token expirado!"
        );

        UsuarioEntity usuario = recuperacao.getUsuario();
        usuario.setSenha(dto.getNovaSenha());

        usuario = autenticacaoService.atualizarSenha(usuario);
        
        usuarioRecuperacaoSenhaRepository.delete(recuperacao);

        emailServiceImpl.notificarSenhaAlterada(usuario);
        
        return usuarioMapper.toResDto(usuario);
    }
    
}
