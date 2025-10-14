package edu.xanderson.PatoCritico.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import edu.xanderson.PatoCritico.model.dtos.ReqTagDTO;
import edu.xanderson.PatoCritico.model.dtos.ResTagDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tag Controller")
public interface TagController {

    @Operation(
        summary = "Adicionar tag ao sistema",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResTagDTO> salvaTag(ReqTagDTO dto); //Admin

    @Operation(
        summary = "Buscar todas as tags registradas",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<List<ResTagDTO>> listarTags(); //Admin

}