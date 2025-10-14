package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.GeneroEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqGeneroDTO {
    private UUID id;
    
    @NotNull
    private GeneroEnum genero;
}