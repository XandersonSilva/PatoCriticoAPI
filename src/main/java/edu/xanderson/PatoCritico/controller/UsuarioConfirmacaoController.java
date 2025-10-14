package edu.xanderson.PatoCritico.controller;


import java.util.UUID;

import org.springframework.http.ResponseEntity;

import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Confirmação de usuário Controller")
public interface UsuarioConfirmacaoController {
    
    @Operation(
        summary = "Banir Usuário",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResUsuarioDTO> inativarUsuario(UUID usuarioId); //Admin
    
    @Operation(
        summary = "Remover banimento de usuário",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResUsuarioDTO> reativarUsuario(UUID usuarioId); //Admin
}