package edu.xanderson.PatoCritico.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.xanderson.PatoCritico.model.dtos.ReqGeneroDTO;
import edu.xanderson.PatoCritico.model.dtos.ResGeneroDTO;
import edu.xanderson.PatoCritico.model.entitys.GeneroEntity;
import edu.xanderson.PatoCritico.model.mappers.GeneroMapper;
import edu.xanderson.PatoCritico.model.repository.GeneroRepository;
import edu.xanderson.PatoCritico.service.GeneroService;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService{
    private final UsuarioAutenticadoImpl usuarioAutenticado;
    
    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;

    @Override
    public ResGeneroDTO salvaGenero(ReqGeneroDTO dto) {
        usuarioAutenticado.verificaPapelAdmin();

        GeneroEntity genero = generoMapper.fromReqDto(dto);
        generoRepository.save(genero);

        return generoMapper.toResDto(genero);
    }

    @Override
    public List<ResGeneroDTO> listarGeneros() {
        usuarioAutenticado.verificaPapelAdmin();
        
        return generoMapper.toResDtoList(generoRepository.findAll());
    }


}
