package edu.xanderson.PatoCritico.service;

import java.util.List;

import edu.xanderson.PatoCritico.model.dtos.ReqGeneroDTO;
import edu.xanderson.PatoCritico.model.dtos.ResGeneroDTO;

public interface GeneroService {

    ResGeneroDTO salvaGenero(ReqGeneroDTO dto); //Admin
    List<ResGeneroDTO> listarGeneros(); //Admin
}