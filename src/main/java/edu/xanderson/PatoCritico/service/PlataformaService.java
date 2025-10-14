package edu.xanderson.PatoCritico.service;

import java.util.List;

import edu.xanderson.PatoCritico.model.dtos.ReqPlataformaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResPlataformaDTO;

public interface PlataformaService {

    ResPlataformaDTO salvaPlataforma(ReqPlataformaDTO dto); //Admin
    List<ResPlataformaDTO> listarPlataformas(); //Usuario
}