package edu.xanderson.PatoCritico.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import edu.xanderson.PatoCritico.model.dtos.ReqDenunciaDTO;
import edu.xanderson.PatoCritico.model.dtos.ResDenunciaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Denuncia controller")
public interface DenunciaController {

    
    @Operation(
        summary = "Denunciar Avaliacao",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResDenunciaDTO> denunciarAvaliacao(ReqDenunciaDTO dto);  //Usuário
    
    @Operation(
        summary = "Busca todas as denuncias registradas na plataforma",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<List<ResDenunciaDTO>> visualizarDenuncias();             //Admin
    
    @Operation(
        summary = "Deleta denuncia e avisa ao autor",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResDenunciaDTO> rejeitarDenuncia(UUID denunciaId);       //Admin
    
    @Operation(
        summary = "Deleta avaliação denunciada e avisa ao autor",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResDenunciaDTO> aceitarDenuncia(UUID denunciaId);        //Admin
}