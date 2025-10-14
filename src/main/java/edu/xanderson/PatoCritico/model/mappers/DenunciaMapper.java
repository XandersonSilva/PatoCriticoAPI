package edu.xanderson.PatoCritico.model.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import edu.xanderson.PatoCritico.model.dtos.ReqDenunciaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResDenunciaDTO;
import edu.xanderson.PatoCritico.model.entitys.DenunciaEntity;

@Mapper(componentModel = "spring")
public interface DenunciaMapper {
    ResDenunciaDTO toResDto(DenunciaEntity denuncia);
    
    DenunciaEntity fromReqDto(ReqDenunciaDTO dto);

    List<ResDenunciaDTO> toResDtoList(List<DenunciaEntity> denuncias);
}