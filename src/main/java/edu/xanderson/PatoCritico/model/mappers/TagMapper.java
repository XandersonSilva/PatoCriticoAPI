package edu.xanderson.PatoCritico.model.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import edu.xanderson.PatoCritico.model.dtos.ReqTagDTO;
import edu.xanderson.PatoCritico.model.dtos.ResTagDTO;
import edu.xanderson.PatoCritico.model.entitys.TagEntity;

@Mapper(componentModel = "spring")
public interface TagMapper {
    ResTagDTO toResDto(TagEntity tag);

    TagEntity fromReqDto(ReqTagDTO dto);

    List<ResTagDTO> toResDtoList(List<TagEntity> tags); 
}
