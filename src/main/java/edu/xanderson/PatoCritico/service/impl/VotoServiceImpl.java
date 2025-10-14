package edu.xanderson.PatoCritico.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.xanderson.PatoCritico.model.dtos.ReqVotoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResVotoDTO;
import edu.xanderson.PatoCritico.model.entitys.AvaliacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.entitys.VotoEntity;
import edu.xanderson.PatoCritico.model.mappers.VotoMapper;
import edu.xanderson.PatoCritico.model.repository.AvaliacaoRepository;
import edu.xanderson.PatoCritico.model.repository.VotoRepository;
import edu.xanderson.PatoCritico.service.VotoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService{
    private final UsuarioAutenticadoImpl usuarioAutenticado;

    private final AvaliacaoRepository avaliacaoRepository;
    private final VotoRepository votoRepository;
    private final VotoMapper votoMapper;
    
    @Override
    @Transactional
    public ResVotoDTO salvarVoto(ReqVotoDTO dto) {
        UsuarioEntity usuario = usuarioAutenticado.usuarioLogado();

        AvaliacaoEntity avaliacao = avaliacaoRepository.getReferenceById(dto.getAvaliacao().getId());
        if (avaliacao == null)throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "O id de avaliação não corresponde a nem uma avaliação registrada!"
        );

        VotoEntity voto = new VotoEntity();

        voto.setAvaliacao(avaliacao);
        voto.setVotante(usuario);
        voto.setTipo(dto.getTipo());

        votoRepository.save(voto);
        
        return votoMapper.toResDto(voto);
    }

    @Override
    public List<ResVotoDTO> listarAvaliacaoVotos(UUID avaliacaoId) {
        return votoMapper.toResDtoList(votoRepository.findByAvaliacaoId(avaliacaoId));
    }
}
