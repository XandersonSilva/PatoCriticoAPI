package edu.xanderson.PatoCritico.service;

import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioRecuperacaoSenhaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;

public interface UsuarioRecuperacaoSenhaService {
    void gerarLinkRecuperarSenha(String usuarioEmail);
    ResUsuarioDTO alterarSenhaUsuario(ReqUsuarioRecuperacaoSenhaDTO dto);
}
