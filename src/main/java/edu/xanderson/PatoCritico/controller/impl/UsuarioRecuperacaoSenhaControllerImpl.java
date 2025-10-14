package edu.xanderson.PatoCritico.controller.impl;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.UsuarioRecuperacaoSenhaController;
import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioRecuperacaoSenhaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import edu.xanderson.PatoCritico.service.impl.UsuarioRecuperacaoSenhaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recuperarSenha")
@RequiredArgsConstructor
public class UsuarioRecuperacaoSenhaControllerImpl implements UsuarioRecuperacaoSenhaController{
    private final UsuarioRecuperacaoSenhaServiceImpl usuarioRecuperacaoSenhaService;

    @Override
    @PostMapping("/{usuarioEmail}/gerarLink")
    public ResponseEntity<Void> gerarLinkRecuperarSenha(@PathVariable @Valid String usuarioEmail) {
        usuarioRecuperacaoSenhaService.gerarLinkRecuperarSenha(usuarioEmail);
        return ResponseEntity.ok().body(null);
    }

    @Override
    @PatchMapping("/atualizar/{token}")
    public ResponseEntity<ResUsuarioDTO> alterarSenhaUsuario(@RequestBody @Validated ReqUsuarioRecuperacaoSenhaDTO dto, @PathVariable @Valid UUID token) {
        dto.setToken_link(token);
        return ResponseEntity.ok().body(usuarioRecuperacaoSenhaService.alterarSenhaUsuario(dto));
    }

}
