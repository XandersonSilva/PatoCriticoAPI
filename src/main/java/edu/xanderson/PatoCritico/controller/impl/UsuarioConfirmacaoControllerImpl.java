package edu.xanderson.PatoCritico.controller.impl;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.UsuarioConfirmacaoController;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import edu.xanderson.PatoCritico.service.impl.UsuarioConfirmacaoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarioConfirmacao")
@RequiredArgsConstructor
public class UsuarioConfirmacaoControllerImpl implements UsuarioConfirmacaoController{
    private final UsuarioConfirmacaoServiceImpl usuarioConfirmacaoService;

    @Override
    @PostMapping("/{usuarioId}/inativar")
    public ResponseEntity<ResUsuarioDTO> inativarUsuario(@PathVariable @Valid UUID usuarioId) {
        return ResponseEntity.ok().body(usuarioConfirmacaoService.inativarUsuario(usuarioId));
    }

    @Override
    @PostMapping("/{usuarioId}/reativar")
    public ResponseEntity<ResUsuarioDTO> reativarUsuario(@PathVariable @Valid UUID usuarioId) {
        return ResponseEntity.ok().body(usuarioConfirmacaoService.reativarUsuario(usuarioId));
    }
}
