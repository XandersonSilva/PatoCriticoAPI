package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.GeneroEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResGeneroDTO {
    private UUID id;
    private GeneroEnum genero;
}