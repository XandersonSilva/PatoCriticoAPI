package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.PlataformaEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResPlataformaDTO {
    private UUID id;
    
    private PlataformaEnum plataforma;
}