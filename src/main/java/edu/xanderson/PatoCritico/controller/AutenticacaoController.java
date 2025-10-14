package edu.xanderson.PatoCritico.controller;


import java.util.UUID;

import org.springframework.http.ResponseEntity;

import edu.xanderson.PatoCritico.model.dtos.ReqLogarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqRegistrarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ResLogarUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticação controller")
public interface AutenticacaoController {
    
    @Operation(
    summary = "Registrar-se na plataforma",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResUsuarioDTO> registrarUsuario(ReqRegistrarUsuarioDTO dto);
    
    @Operation(
    summary = "Entrar na plataforma",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResLogarUsuarioDTO> logarUsuario(ReqLogarUsuarioDTO dto);
    
    @Operation(
    summary = "Validar conta via código",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResUsuarioDTO> validarUsuario(UUID codigo);
}
