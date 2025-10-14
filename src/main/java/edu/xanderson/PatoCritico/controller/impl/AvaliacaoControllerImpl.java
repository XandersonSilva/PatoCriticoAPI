package edu.xanderson.PatoCritico.controller.impl;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.AvaliacaoController;
import edu.xanderson.PatoCritico.model.dtos.ReqAvaliacaoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResAvaliacaoDTO;
import edu.xanderson.PatoCritico.service.impl.AvaliacaoServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/avaliacao")
public class AvaliacaoControllerImpl implements AvaliacaoController{
    private final AvaliacaoServiceImpl avaliacaoService;
    
    
    @Override
    @PostMapping("/salvar")
    public ResponseEntity<ResAvaliacaoDTO> salvarAvaliacao(@RequestBody @Validated ReqAvaliacaoDTO dto){
        ResAvaliacaoDTO avaliacao = avaliacaoService.salvarAvaliacao(dto);

        return ResponseEntity.ok().body(avaliacao);
    }

    @Override
    @PutMapping("/editar")
    public ResponseEntity<ResAvaliacaoDTO> editarAvaliacao(@RequestBody @Validated ReqAvaliacaoDTO dto) {
        ResAvaliacaoDTO avaliacao = avaliacaoService.editarAvaliacao(dto);
        return ResponseEntity.ok().body(avaliacao);
    }

    @Override
    @DeleteMapping("/apagar/{avaliacaoId}")
    public ResponseEntity<ResAvaliacaoDTO> excluirAvaliacao(@PathVariable @Validated UUID avaliacaoId) {
        ResAvaliacaoDTO avaliacao = avaliacaoService.excluirAvaliacao(avaliacaoId);
        return ResponseEntity.ok().body(avaliacao);
    }

    @Override
    @DeleteMapping("/apagarAvaliacaoImpropria/{avaliacaoId}")
    public ResponseEntity<ResAvaliacaoDTO> removerAvaliacaoImpropria(@PathVariable @Validated UUID avaliacaoId) {
        ResAvaliacaoDTO avaliacao = avaliacaoService.excluirAvaliacao(avaliacaoId);
        return ResponseEntity.ok().body(avaliacao);
    }

}
