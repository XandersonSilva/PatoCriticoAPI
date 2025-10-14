package edu.xanderson.PatoCritico.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.VotoController;
import edu.xanderson.PatoCritico.model.dtos.ReqVotoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResVotoDTO;
import edu.xanderson.PatoCritico.service.impl.VotoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/voto")
@RequiredArgsConstructor
public class VotoControllerImpl implements VotoController{
    private final VotoServiceImpl votoService;

    @Override
    @PostMapping("/salvar")
    public ResponseEntity<ResVotoDTO> salvarVoto(@RequestBody @Validated ReqVotoDTO dto) {
        return ResponseEntity.ok().body(votoService.salvarVoto(dto));
    }

    @Override
    @GetMapping("/{avaliacaoId}/votos")
    public ResponseEntity<List<ResVotoDTO>> listarAvaliacaoVotos(@PathVariable @Valid UUID avaliacaoId) {
        return ResponseEntity.ok().body(votoService.listarAvaliacaoVotos(avaliacaoId));
    }
    
}
