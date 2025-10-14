package edu.xanderson.PatoCritico.model.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import edu.xanderson.PatoCritico.model.dtos.ReqGeneroDTO;
import edu.xanderson.PatoCritico.model.dtos.ResGeneroDTO;
import edu.xanderson.PatoCritico.model.entitys.GeneroEntity;

@Mapper(componentModel = "spring")
public interface GeneroMapper {
    ResGeneroDTO toResDto(GeneroEntity genero);

    GeneroEntity fromReqDto(ReqGeneroDTO dto);

    List<ResGeneroDTO> toResDtoList(List<GeneroEntity> generos);
}
