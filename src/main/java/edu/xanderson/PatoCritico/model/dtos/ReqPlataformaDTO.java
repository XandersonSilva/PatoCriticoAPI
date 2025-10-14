package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.PlataformaEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqPlataformaDTO {

    private UUID id;
    
    @NotNull
    private PlataformaEnum plataforma;

    private UUID jogoId;
}