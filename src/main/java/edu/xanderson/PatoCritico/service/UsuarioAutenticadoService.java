package edu.xanderson.PatoCritico.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;

public interface UsuarioAutenticadoService {
    UsuarioEntity usuarioLogado() throws UserPrincipalNotFoundException;
    UsuarioEntity verificaPapelAdmin();
}
