package edu.xanderson.PatoCritico.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.enums.PapelEnum;
import edu.xanderson.PatoCritico.service.UsuarioAutenticadoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioAutenticadoImpl implements UsuarioAutenticadoService{
    
    @Override
    public UsuarioEntity usuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException("Usuário não encontrado \n" + e);
        }
    }

    @Override
    public UsuarioEntity verificaPapelAdmin(){
        UsuarioEntity user = usuarioLogado();
        if (user.getPapel() != PapelEnum.ADMINISTRADOR) 
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O id do jogo deve ser informado para edita-lo!"
            );
        return user;
    }
    
}
