package edu.xanderson.PatoCritico.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.xanderson.PatoCritico.model.dtos.ReqDenunciaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResDenunciaDTO;
import edu.xanderson.PatoCritico.model.entitys.AvaliacaoEntity;
import edu.xanderson.PatoCritico.model.entitys.DenunciaEntity;
import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import edu.xanderson.PatoCritico.model.mappers.DenunciaMapper;
import edu.xanderson.PatoCritico.model.repository.AvaliacaoRepository;
import edu.xanderson.PatoCritico.model.repository.DenunciaRepository;
import edu.xanderson.PatoCritico.service.DenunciaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DenunciaServiceImpl implements DenunciaService{
    private final UsuarioAutenticadoImpl usuarioAutenticado;

    private final EmailServiceImpl emailService;
    private final DenunciaMapper denunciaMapper;
    private final DenunciaRepository denunciaRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoServiceImpl avaliacaoService;

    @Override
    public ResDenunciaDTO denunciarAvaliacao(ReqDenunciaDTO dto) {
        UsuarioEntity user = usuarioAutenticado.usuarioLogado();
        Optional<AvaliacaoEntity> avaliacao = avaliacaoRepository.findById(dto.getAvaliacao().getId());

        if (!avaliacao.isPresent()) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Código de avaliação inválido!"
        );

        DenunciaEntity denuncia = denunciaMapper.fromReqDto(dto);
        denuncia.setDenunciante(user);
        denunciaRepository.save(denuncia);
        return denunciaMapper.toResDto(denuncia);
    }

    @Override
    public List<ResDenunciaDTO> visualizarDenuncias() {
        usuarioAutenticado.verificaPapelAdmin();

        return denunciaMapper.toResDtoList(denunciaRepository.findAll());
    }

    @Override
    @Transactional
    public ResDenunciaDTO rejeitarDenuncia(UUID denunciaId) {
        usuarioAutenticado.verificaPapelAdmin();

        if(denunciaId == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Id da denuncia não informado"
        );
        DenunciaEntity denuncia = denunciaRepository.findById(denunciaId).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Denuncia inexistente!"
            )
        );

        denunciaRepository.delete(denuncia);

        emailService.notificarResultadoDenunciaNegativo(denuncia);

        return denunciaMapper.toResDto(denuncia);
    }

    @Override
    @Transactional
    public ResDenunciaDTO aceitarDenuncia(UUID denunciaId) {
        usuarioAutenticado.verificaPapelAdmin();

        if(denunciaId == null) throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Id da denuncia não informado"
        );
        DenunciaEntity denuncia = denunciaRepository.findById(denunciaId).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Denuncia inexistente!"
            )
        );

        denunciaRepository.delete(denuncia);

        avaliacaoService.removerAvaliacaoImpropria(denuncia.getAvaliacao().getId());

        emailService.notificarResultadoDenunciaPositivo(denuncia);

        return denunciaMapper.toResDto(denuncia);
    }
    
}
