package edu.xanderson.PatoCritico.service;

import java.util.List;
import java.util.UUID;

import edu.xanderson.PatoCritico.model.dtos.ReqGeneroJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqPlataformaDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqTagJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResAvaliacaoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResJogoDTO;

public interface JogoService {

    ResJogoDTO cadastrarJogo(ReqJogoDTO dto);   //Admin
    ResJogoDTO editarJogo(ReqJogoDTO dto);      //Admin
    ResJogoDTO adicionarTagJogo(ReqTagJogoDTO dto);   //Admin
    ResJogoDTO adicionarPlataformaJogo(ReqPlataformaDTO dto);   //Admin
    ResJogoDTO informarGeneroJogo(ReqGeneroJogoDTO dto); //Admin
    ResJogoDTO apagarJogo(UUID jogoId);         //Admin
    List<ResAvaliacaoDTO> buscarTodasAvaliacoes(UUID jogoId); //Usuário
    List<ResJogoDTO> visualizarCatalogo();                    //Usuário
    ResJogoDTO buscarJogo(UUID jogoId);                       //Usuário

}