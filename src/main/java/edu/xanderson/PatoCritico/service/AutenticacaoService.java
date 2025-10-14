package edu.xanderson.PatoCritico.service;


import java.util.UUID;

import edu.xanderson.PatoCritico.model.dtos.ReqLogarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqRegistrarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ResLogarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;

public interface AutenticacaoService {
    ResUsuarioDTO registrarUsuario(ReqRegistrarUsuarioDTO dto);
    ResLogarUsuarioDTO logarUsuario(ReqLogarUsuarioDTO dto);
    ResUsuarioDTO validarUsuario(UUID codigo);
}
