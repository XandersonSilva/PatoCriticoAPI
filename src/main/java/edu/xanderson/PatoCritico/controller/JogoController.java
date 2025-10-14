package edu.xanderson.PatoCritico.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import edu.xanderson.PatoCritico.model.dtos.ReqGeneroJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqPlataformaDTO;
import edu.xanderson.PatoCritico.model.dtos.ReqTagJogoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResAvaliacaoDTO;
import edu.xanderson.PatoCritico.model.dtos.ResJogoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Jogo Controller")
public interface JogoController {

    @Operation(
    summary = "Cadastrar um novo jogo",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResJogoDTO> cadastrarJogo(ReqJogoDTO dto);   //Admin

    @Operation(
    summary = "Editar um jogo",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResJogoDTO> editarJogo(ReqJogoDTO dto);      //Admin

    @Operation(
    summary = "Adicionar tag a um jogo",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResJogoDTO> adicionarTagJogo(ReqTagJogoDTO dto);      //Admin

    @Operation(
    summary = "Informar genero de um jogo",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResJogoDTO> informarGeneroJogo(ReqGeneroJogoDTO dto); //Admin

    @Operation(
    summary = "Apagar jogo",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResJogoDTO> apagarJogo(UUID jogoId);                  //Admin
    
    @Operation(
    summary = "Busca todas as avaliações de um jogo",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<List<ResAvaliacaoDTO>> buscarTodasAvaliacoes(UUID jogoId); //Usuário
    
    @Operation(
    summary = "Busca todos os jogos cadastrados no sistema",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<List<ResJogoDTO>> visualizarCatalogo();                    //Usuário
    
    @Operation(
    summary = "Buscar um jogo",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResJogoDTO> buscarJogo(UUID jogoId);                       //Usuário

    @Operation(
    summary = "Adicionar ao jogo sua plataforma pertencente",
        responses = {
            @ApiResponse(responseCode = "200"), 
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden"),
            @ApiResponse(responseCode = "404", ref = "notFound"),
            @ApiResponse(responseCode = "500", ref = "internalServerError")
        }
    )
    ResponseEntity<ResJogoDTO> adicionarPlataformaJogo(ReqPlataformaDTO dto); //Admin

}