package edu.xanderson.PatoCritico.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import edu.xanderson.PatoCritico.model.dtos.ReqGeneroDTO;
import edu.xanderson.PatoCritico.model.dtos.ResGeneroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Gênero controller")
public interface GeneroController {

    
    @Operation(
        summary = "Adicionar novo gênero a tabela de possibilidades",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResGeneroDTO> salvaGenero(ReqGeneroDTO dto); //Admin
    
    @Operation(
        summary = "Buscar todos os gêneros cadastrados",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<List<ResGeneroDTO>> listarGeneros(); //Admin
}