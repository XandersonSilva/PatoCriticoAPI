package edu.xanderson.PatoCritico.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.xanderson.PatoCritico.model.repository.UsuarioRepository;
import edu.xanderson.PatoCritico.service.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService{
    private final UsuarioRepository usuarioRepository;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails usuario = usuarioRepository.findByEmail(username);
        if (usuario instanceof UserDetails) return usuario;
        
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
    
}
