package edu.xanderson.PatoCritico.service;

import java.util.List;

import edu.xanderson.PatoCritico.model.dtos.ReqTagDTO;
import edu.xanderson.PatoCritico.model.dtos.ResTagDTO;

public interface TagService {

    ResTagDTO salvaTag(ReqTagDTO dto); //Admin
    List<ResTagDTO> listarTags(); //Admin

}