package edu.xanderson.PatoCritico.service.impl;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import edu.xanderson.PatoCritico.model.dtos.ReqLogarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqRegistrarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ResLogarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import edu.xanderson.PatoCritico.model.entitys.UsuarioConfirmacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.enums.UsuarioEstadoEnum;
import edu.xanderson.PatoCritico.model.mappers.UsuarioMapper;
import edu.xanderson.PatoCritico.model.repository.UsuarioConfirmacaoRepository;
import edu.xanderson.PatoCritico.model.repository.UsuarioRepository;
import edu.xanderson.PatoCritico.service.AutenticacaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticacaoServiceImpl implements AutenticacaoService{
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConfirmacaoServiceImpl usuarioConfirmacaoService;
    private final UsuarioConfirmacaoRepository usuarioConfirmacaoRepository;
    private final UsuarioMapper usuarioMapper;


    @Override
    @Transactional
    public ResUsuarioDTO registrarUsuario(ReqRegistrarUsuarioDTO dto) {
        String senha = new BCryptPasswordEncoder().encode(dto.getSenha());

        UsuarioEntity usuario = (UsuarioEntity) usuarioRepository.findByEmail(dto.getEmail());
        if (usuario != null) {
            if (usuario.getUsusarioEstado() == UsuarioEstadoEnum.PENDENTE) {
                usuario.setSenha(senha);

                usuarioRepository.save(usuario);
                usuarioConfirmacaoService.registrarUsuarioRecem_Criado(usuario);
                
                return null;
            }
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O email informado já está em uso!"
            );
        }

        UsuarioEntity novoUsuario =  new UsuarioEntity();
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setUsusarioEstado(UsuarioEstadoEnum.PENDENTE);
        novoUsuario.setSenha(senha);

        usuarioRepository.save(novoUsuario);
        usuarioConfirmacaoService.registrarUsuarioRecem_Criado(novoUsuario);

        return usuarioMapper.toResDto(novoUsuario);
    }

    @Override
    public ResLogarUsuarioDTO logarUsuario(ReqLogarUsuarioDTO dto) {
        UsuarioEntity usuario = (UsuarioEntity) usuarioRepository.findByEmail(dto.getEmail());

        if (usuario.getUsusarioEstado() == UsuarioEstadoEnum.INATIVO) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Usuário banido!"
        );

        if (usuario.getUsusarioEstado() != UsuarioEstadoEnum.ATIVO) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "O usuário não possui registro ativo na plataforma!"
        );

        UsernamePasswordAuthenticationToken usernamePassword;
        usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((UsuarioEntity) auth.getPrincipal());
        
        ResLogarUsuarioDTO loginRes = new ResLogarUsuarioDTO();
        loginRes.setToken(token);

        return loginRes;
    }

    @Override
    @Transactional
    public ResUsuarioDTO validarUsuario(UUID codigo) {
        if (codigo == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "código não informado!"
        );
        // UUID cod = UUID.fromString(codigo);
        UsuarioConfirmacaoEntity confrimacao = usuarioConfirmacaoRepository.findByCode(codigo);

        if (confrimacao == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "nem um usuário corresponde ao código informado!"
        );

        UsuarioEntity usuario = usuarioRepository.getReferenceById(confrimacao.getUsuario().getId());
        
        usuario.setUsusarioEstado(UsuarioEstadoEnum.ATIVO);
        usuarioRepository.save(usuario);

        usuarioConfirmacaoRepository.delete(confrimacao);

        return usuarioMapper.toResDto(usuario);
    }

    @Transactional
    protected UsuarioEntity atualizarSenha(UsuarioEntity usuario) {
        String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senha);

        usuarioRepository.save(usuario);
        return usuario;
    }
    
}
