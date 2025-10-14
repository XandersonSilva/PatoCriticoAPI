package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqGeneroJogoDTO {
    
    private UUID genero;
    
    private UUID jogo;
}
