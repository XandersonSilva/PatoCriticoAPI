package edu.xanderson.PatoCritico.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioRecuperacaoSenhaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Tag(name = "Recuperar senha controller")
public interface UsuarioRecuperacaoSenhaController {
    
    @Operation(
    summary = "Gerar link para recuperar senha",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<Void> gerarLinkRecuperarSenha(String usuarioEmail);
    
    @Operation(
    summary = "Atualizar a senha se o token for valido",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResUsuarioDTO> alterarSenhaUsuario(ReqUsuarioRecuperacaoSenhaDTO dto, UUID token);
}
