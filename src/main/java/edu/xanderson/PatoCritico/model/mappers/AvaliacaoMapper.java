package edu.xanderson.PatoCritico.model.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import edu.xanderson.PatoCritico.model.dtos.ReqAvaliacaoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResAvaliacaoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResVotoDTO;
import edu.xanderson.PatoCritico.model.entitys.AvaliacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.VotoEntity;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {
    
    ReqAvaliacaoDTO toReqDto(ReqAvaliacaoDTO avaliacao);
    ResAvaliacaoDTO toResDto(AvaliacaoEntity avaliacao);

    AvaliacaoEntity fromReqDto(ReqAvaliacaoDTO dto);
    AvaliacaoEntity fromResDto(ResAvaliacaoDTO dto);



    List<ResAvaliacaoDTO> toResDtoList(List<AvaliacaoEntity> avaliacao);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ReqAvaliacaoDTO dto, @MappingTarget AvaliacaoEntity avaliacao);


    //Método "interno" para converter de VotoEntity para ResVotoDTO
    //Dentro do "toResDto"  
    ResVotoDTO toResDto(VotoEntity voto);    
}