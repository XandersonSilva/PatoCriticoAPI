package edu.xanderson.PatoCritico.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import edu.xanderson.PatoCritico.controller.JogoController;
import edu.xanderson.PatoCritico.model.dtos.ReqGeneroJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqPlataformaDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqTagJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResAvaliacaoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResJogoDTO;
import edu.xanderson.PatoCritico.service.impl.JogoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jogo")
public class JogoControllerImpl implements JogoController{
    private final JogoServiceImpl jogoService;

    @Override
    @PostMapping("/salvar")
    public ResponseEntity<ResJogoDTO> cadastrarJogo(@RequestBody @Validated ReqJogoDTO dto) {
        ResJogoDTO dtoRes = jogoService.cadastrarJogo(dto);
        return ResponseEntity.ok().body(dtoRes);
    }

    @Override
    @PutMapping("/editar")
    public ResponseEntity<ResJogoDTO> editarJogo(@RequestBody @Validated ReqJogoDTO dto){
        ResJogoDTO dtoRes = jogoService.editarJogo(dto);
        return ResponseEntity.ok().body(dtoRes);
    }

    @Override
    @PatchMapping("/adicionarTag")
    public ResponseEntity<ResJogoDTO> adicionarTagJogo(@RequestBody @Validated ReqTagJogoDTO dto) {
        ResJogoDTO dtoRes = jogoService.adicionarTagJogo(dto);
        return ResponseEntity.ok().body(dtoRes);
    }

    @Override
    @PatchMapping("/informarGenero")
    public ResponseEntity<ResJogoDTO> informarGeneroJogo(@RequestBody @Validated ReqGeneroJogoDTO dto) {
        ResJogoDTO dtoRes = jogoService.informarGeneroJogo(dto);
        return ResponseEntity.ok().body(dtoRes);
    }

    @Override
    @PatchMapping("/adicionarPlataforma")
    public ResponseEntity<ResJogoDTO> adicionarPlataformaJogo(@RequestBody @Validated ReqPlataformaDTO dto) {
        ResJogoDTO dtoRes = jogoService.adicionarPlataformaJogo(dto);
        return ResponseEntity.ok().body(dtoRes);
    }

    @Override
    @DeleteMapping("/apagar")
    public ResponseEntity<ResJogoDTO> apagarJogo(UUID jogoId) {
        ResJogoDTO dtoRes = jogoService.apagarJogo(jogoId);
        return ResponseEntity.ok().body(dtoRes);
    }

    @Override
    @GetMapping("/{jogoId}/avaliacoes")
    public ResponseEntity<List<ResAvaliacaoDTO>> buscarTodasAvaliacoes(@PathVariable @Valid UUID jogoId) {
        List<ResAvaliacaoDTO> dtoRes = jogoService.buscarTodasAvaliacoes(jogoId);
        return ResponseEntity.ok().body(dtoRes);
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<ResJogoDTO>> visualizarCatalogo() {
        List<ResJogoDTO> dtoRes = jogoService.visualizarCatalogo();
        return ResponseEntity.ok().body(dtoRes);
    }

    @Override
    @GetMapping("/{jogoId}")
    public ResponseEntity<ResJogoDTO> buscarJogo(@PathVariable @Valid UUID jogoId) {
        ResJogoDTO dtoRes = jogoService.buscarJogo(jogoId);
        return ResponseEntity.ok().body(dtoRes);
    }

    
}
