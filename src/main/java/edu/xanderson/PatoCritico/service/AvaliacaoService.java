package edu.xanderson.PatoCritico.service;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.dtos.ReqAvaliacaoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResAvaliacaoDTO;

public interface AvaliacaoService {

    ResAvaliacaoDTO salvarAvaliacao(ReqAvaliacaoDTO dto); //Usuário
    ResAvaliacaoDTO editarAvaliacao(ReqAvaliacaoDTO dto); //Usuário
    ResAvaliacaoDTO excluirAvaliacao(UUID avaliacaoId);   //Usuário
    ResAvaliacaoDTO removerAvaliacaoImpropria(UUID avaliacaoId); //Admin

}