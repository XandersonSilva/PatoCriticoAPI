package edu.xanderson.PatoCritico.model.mappers;

import org.mapstruct.Mapper;

import edu.xanderson.PatoCritico.model.dtos.ReqEmailDTO;
import edu.xanderson.PatoCritico.model.entitys.EmailEntity;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    EmailEntity fromReqDto(ReqEmailDTO dto);
}
