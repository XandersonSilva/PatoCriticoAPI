package edu.xanderson.PatoCritico.model.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import edu.xanderson.PatoCritico.model.dtos.ReqVotoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResVotoDTO;
import edu.xanderson.PatoCritico.model.entitys.VotoEntity;

@Mapper(componentModel = "spring")
public interface VotoMapper {
    ResVotoDTO toResDto(VotoEntity voto);

    VotoEntity fromReqDto(ReqVotoDTO dto);


    List<ResVotoDTO> toResDtoList(List<VotoEntity> votos);
}
