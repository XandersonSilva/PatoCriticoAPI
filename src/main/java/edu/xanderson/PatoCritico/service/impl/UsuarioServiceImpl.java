package edu.xanderson.PatoCritico.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioPapelDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.mappers.UsuarioMapper;
import edu.xanderson.PatoCritico.model.repository.UsuarioRepository;
import edu.xanderson.PatoCritico.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioAutenticadoImpl usuarioAutenticado;
    private final AutenticacaoServiceImpl autenticacaoService;

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public ResUsuarioDTO editarInformacoesPerfil(ReqUsuarioDTO dto) {
        UsuarioEntity usuario = existeUsuario(dto.getId());
        usuarioMapper.updateFromDto(dto, usuario);
        
        usuarioRepository.save(usuario);
        return usuarioMapper.toResDto(usuario);
    }

    @Override
    public ResUsuarioDTO excluirConta(UUID usuarioId) {
        UsuarioEntity usuario = existeUsuario(usuarioId);

        usuarioRepository.delete(usuario);
        return usuarioMapper.toResDto(usuario);
        
    }

    @Override
    public List<ResUsuarioDTO> listarUsuarios() {
        usuarioAutenticado.verificaPapelAdmin();

        return usuarioMapper.toResDtoList(usuarioRepository.findAll());
    }

    @Override
    public ResUsuarioDTO alterarPapelUsuario(ReqUsuarioPapelDTO dto) {
        usuarioAutenticado.verificaPapelAdmin();

        UsuarioEntity usuario = existeUsuario(dto.getUsuarioId());

        
        usuario.setPapel(dto.getPapel());
        usuarioRepository.save(usuario);

        return usuarioMapper.toResDto(usuario);
    }

    private UsuarioEntity existeUsuario(UUID usuarioId){
        if (usuarioId == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Id do usuário não informado!"
        );
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O id informado não corresponde a um usuário!"
            ));

        return usuario;    
    }


}
