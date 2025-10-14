package edu.xanderson.PatoCritico.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;


import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqUsuarioPapelDTO;
import edu.xanderson.PatoCritico.model.dtos.ResUsuarioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Tag(name = "Usuário controller")
public interface UsuarioController {

    
    @Operation(
    summary = "Editar informações da conta",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResUsuarioDTO> editarInformacoesPerfil(ReqUsuarioDTO dto);   //Usuário
    
    @Operation(
    summary = "Excluir conta",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResUsuarioDTO> excluirConta(UUID usuarioId);                 //Usuário
    
    @Operation(
    summary = "Buscar todos os usuários",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<List<ResUsuarioDTO>> listarUsuarios();                       //Admin
    
    @Operation(
    summary = "Alterar papel do usuário",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResUsuarioDTO> alterarPapelUsuario(ReqUsuarioPapelDTO dto);  //Admin

}