package edu.xanderson.PatoCritico.model.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import edu.xanderson.PatoCritico.model.dtos.ReqPlataformaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResPlataformaDTO;
import edu.xanderson.PatoCritico.model.entitys.PlataformaEntity;

@Mapper(componentModel = "spring")
public interface PlataformaMapper {
    ResPlataformaDTO toResDto(PlataformaEntity plataforma);

    PlataformaEntity fromReqDto(ReqPlataformaDTO dto);

    List<ResPlataformaDTO> toResDtoList(List<PlataformaEntity> plataformas);
}