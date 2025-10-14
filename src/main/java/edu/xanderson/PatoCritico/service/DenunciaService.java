package edu.xanderson.PatoCritico.service;

import java.util.List;
import java.util.UUID;

import edu.xanderson.PatoCritico.model.dtos.ReqDenunciaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResDenunciaDTO;

public interface DenunciaService {

    ResDenunciaDTO denunciarAvaliacao(ReqDenunciaDTO dto);  //Usuário
    List<ResDenunciaDTO> visualizarDenuncias();             //Admin
    ResDenunciaDTO rejeitarDenuncia(UUID denunciaId);       //Admin
    ResDenunciaDTO aceitarDenuncia(UUID denunciaId);        //Admin
}