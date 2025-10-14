package edu.xanderson.PatoCritico.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.UsuarioJogoController;
import edu.xanderson.PatoCritico.model.dtos.ResJogoDTO;
import edu.xanderson.PatoCritico.service.impl.UsuarioJogoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuarioJogo")
@RequiredArgsConstructor
public class UsuarioJogoControllerImpl implements UsuarioJogoController{
    private final UsuarioJogoServiceImpl usuarioJogoService;

    @Override
    @PatchMapping("/{jogoId}/desejo")
    public ResponseEntity<ResJogoDTO> tornarJogoDesejo(@PathVariable @Valid UUID jogoId) {
        return ResponseEntity.ok().body(usuarioJogoService.tornarJogoDesejo(jogoId));
    }

    @Override
    @PatchMapping("/{jogoId}/jogando")
    public ResponseEntity<ResJogoDTO> tornarJogoJogando(@PathVariable @Valid UUID jogoId) {
        return ResponseEntity.ok().body(usuarioJogoService.tornarJogoJogando(jogoId));
    }

    @Override
    @PatchMapping("/{jogoId}/finalizado")
    public ResponseEntity<ResJogoDTO> tornarJogoFinalizado(@PathVariable @Valid UUID jogoId) {
        return ResponseEntity.ok().body(usuarioJogoService.tornarJogoFinalizado(jogoId));
    }

    @Override
    @DeleteMapping("/{jogoId}/removerJogoBiblioteca")
    public ResponseEntity<ResJogoDTO> removerJogoBibliotecaPessoal(@PathVariable @Valid UUID jogoId) {
        return ResponseEntity.ok().body(usuarioJogoService.removerJogoBibliotecaPessoal(jogoId));
    }

    @Override
    @GetMapping("/visualizarBiblioteca")
    public ResponseEntity<List<ResJogoDTO>> visualizarBibliotecaPessoal() {
        return ResponseEntity.ok().body(usuarioJogoService.visualizarBibliotecaPessoal());
    }

}
