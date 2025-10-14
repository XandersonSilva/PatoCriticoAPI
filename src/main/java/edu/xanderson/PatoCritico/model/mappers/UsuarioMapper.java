package edu.xanderson.PatoCritico.model.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    ReqUsuarioDTO toReqDto(UsuarioEntity usuario);
    ResUsuarioDTO toResDto(UsuarioEntity usuario);
    
    UsuarioEntity fromReqDto(ReqUsuarioDTO usuario);
    UsuarioEntity fromResDto(ResUsuarioDTO usuario);    
    

    List<ResUsuarioDTO> toResDtoList(List<UsuarioEntity> usuarios);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ReqUsuarioDTO dto, @MappingTarget UsuarioEntity usuario);
}