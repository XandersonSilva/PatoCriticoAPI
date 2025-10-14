package edu.xanderson.PatoCritico.service;

import java.util.List;
import java.util.UUID;

import edu.xanderson.PatoCritico.model.dtos.ReqVotoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResVotoDTO;

public interface VotoService {

    ResVotoDTO salvarVoto(ReqVotoDTO dto); //Usuário
    List<ResVotoDTO> listarAvaliacaoVotos(UUID avaliacaoId); //Usuário
}