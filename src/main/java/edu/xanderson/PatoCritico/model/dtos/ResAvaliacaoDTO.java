package edu.xanderson.PatoCritico.model.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResAvaliacaoDTO {

    private UUID id;
        
    private String titulo;
    
    private String descricao;
    
    private Integer nota;
    
    private LocalDate dataPostagem;

    private List<ResVotoDTO> votos;
}