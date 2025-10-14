package edu.xanderson.PatoCritico.service;

import java.util.List;
import java.util.UUID;


import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioPapelDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;

public interface UsuarioService {

    ResUsuarioDTO editarInformacoesPerfil(ReqUsuarioDTO dto);   //Usuário
    ResUsuarioDTO excluirConta(UUID usuarioId);                 //Usuário
    List<ResUsuarioDTO> listarUsuarios();                       //Admin
    ResUsuarioDTO alterarPapelUsuario(ReqUsuarioPapelDTO dto);  //Admin

}