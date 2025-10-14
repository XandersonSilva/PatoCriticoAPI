package edu.xanderson.PatoCritico.service;


import java.util.UUID;

import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;

public interface UsuarioConfirmacaoService {

    UUID registrarUsuarioRecem_Criado(UsuarioEntity usuario); //Sistema
    ResUsuarioDTO ativarUsuario(UUID usuarioId);   //Sistema
    ResUsuarioDTO inativarUsuario(UUID usuarioId); //Admin
    ResUsuarioDTO reativarUsuario(UUID usuarioId); //Admin
}