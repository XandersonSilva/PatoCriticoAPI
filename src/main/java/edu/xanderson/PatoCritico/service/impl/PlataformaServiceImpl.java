package edu.xanderson.PatoCritico.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.xanderson.PatoCritico.model.dtos.ReqPlataformaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResPlataformaDTO;
import edu.xanderson.PatoCritico.model.entitys.PlataformaEntity;
import edu.xanderson.PatoCritico.model.enums.PlataformaRepositiory;
import edu.xanderson.PatoCritico.model.mappers.PlataformaMapper;
import edu.xanderson.PatoCritico.service.PlataformaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlataformaServiceImpl implements PlataformaService{
    private final UsuarioAutenticadoImpl usuarioAutenticado;

    private final PlataformaRepositiory plataformaRepositiory;
    private final PlataformaMapper plataformaMapper;

    @Override
    public ResPlataformaDTO salvaPlataforma(ReqPlataformaDTO dto) {
        usuarioAutenticado.verificaPapelAdmin();

        PlataformaEntity plataforma = plataformaRepositiory.findByPlataforma(dto.getPlataforma());
        if (plataforma != null) throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "Plataforma já registrada!"  
        );

        plataformaRepositiory.save(plataformaMapper.fromReqDto(dto));

        return plataformaMapper.toResDto(plataforma);
    }

    @Override
    public List<ResPlataformaDTO> listarPlataformas() {
        return plataformaMapper.toResDtoList(plataformaRepositiory.findAll());
    }
    
}
