package edu.xanderson.PatoCritico.model.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import edu.xanderson.PatoCritico.model.dtos.ReqJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResJogoDTO;
import edu.xanderson.PatoCritico.model.entitys.JogoEntity;

@Mapper(componentModel = "spring")
public interface JogoMapper {
    ResJogoDTO toResDTO(JogoEntity jogo);
    ReqJogoDTO toReqDTO(JogoEntity jogo);

    JogoEntity toEntity(ReqJogoDTO dto);
    JogoEntity toEntity(ResJogoDTO dto);
    List<ResJogoDTO> toDTOList(List<JogoEntity> jogos);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ReqJogoDTO dto, @MappingTarget JogoEntity jogo);
}