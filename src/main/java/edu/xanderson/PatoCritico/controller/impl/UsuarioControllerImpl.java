package edu.xanderson.PatoCritico.controller.impl;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.UsuarioController;
import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioPapelDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import edu.xanderson.PatoCritico.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController{
    private final UsuarioServiceImpl usuarioService;


    @Override
    @PutMapping("/editar")
    public ResponseEntity<ResUsuarioDTO> editarInformacoesPerfil(@RequestBody @Validated ReqUsuarioDTO dto) {
        return ResponseEntity.ok().body(usuarioService.editarInformacoesPerfil( dto));
    }

    @Override
    @DeleteMapping("/apagar")
    public ResponseEntity<ResUsuarioDTO> excluirConta() {
        return ResponseEntity.ok().body(usuarioService.excluirConta());
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<ResUsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }

    @Override
    @PatchMapping("/mudarPapel")
    public ResponseEntity<ResUsuarioDTO> alterarPapelUsuario(@RequestBody @Validated ReqUsuarioPapelDTO dto) {
        return ResponseEntity.ok().body(usuarioService.alterarPapelUsuario(dto));
    };

}
