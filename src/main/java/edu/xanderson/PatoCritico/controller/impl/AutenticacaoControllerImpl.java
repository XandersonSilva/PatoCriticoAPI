package edu.xanderson.PatoCritico.controller.impl;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.PatoCritico.controller.AutenticacaoController;
import edu.xanderson.PatoCritico.model.dtos.ReqLogarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqRegistrarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ResLogarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import edu.xanderson.PatoCritico.service.impl.AutenticacaoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoControllerImpl implements AutenticacaoController{
    private final AutenticacaoServiceImpl autenticacaoService;
    
    @Override
    @PostMapping("/registrar")
    public ResponseEntity<ResUsuarioDTO> registrarUsuario(@RequestBody @Validated ReqRegistrarUsuarioDTO dto){
        ResUsuarioDTO user = autenticacaoService.registrarUsuario(dto);
        return ResponseEntity.ok().body(user);
    }

    @Override
    @PostMapping("/entrar")
    public ResponseEntity<ResLogarUsuarioDTO> logarUsuario(@RequestBody @Validated ReqLogarUsuarioDTO dto){
        ResLogarUsuarioDTO user = autenticacaoService.logarUsuario(dto);
        return ResponseEntity.ok().body(user);
    }

    @Override
    @GetMapping("/validar/{code}")
    public ResponseEntity<ResUsuarioDTO> validarUsuario(@PathVariable("code") @Valid UUID codigoValidacao){
        ResUsuarioDTO user = autenticacaoService.validarUsuario(codigoValidacao);

        return ResponseEntity.ok().body(user);
    }
}
