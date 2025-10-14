package edu.xanderson.PatoCritico.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.xanderson.PatoCritico.model.dtos.ReqTagDTO;
import edu.xanderson.PatoCritico.model.dtos.ResTagDTO;
import edu.xanderson.PatoCritico.model.entitys.TagEntity;
import edu.xanderson.PatoCritico.model.mappers.TagMapper;
import edu.xanderson.PatoCritico.model.repository.TagRepository;
import edu.xanderson.PatoCritico.service.TagService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{
    private final UsuarioAutenticadoImpl usuarioAutenticado;

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public ResTagDTO salvaTag(ReqTagDTO dto) {
        usuarioAutenticado.verificaPapelAdmin();

        TagEntity tag = tagMapper.fromReqDto(dto);
        tagRepository.save(tag);
        
        return tagMapper.toResDto(tag);
    }

    @Override
    public List<ResTagDTO> listarTags() {
        usuarioAutenticado.verificaPapelAdmin();
        
        return tagMapper.toResDtoList(tagRepository.findAll());
    }

}
