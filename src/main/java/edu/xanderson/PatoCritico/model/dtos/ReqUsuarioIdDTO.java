package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReqUsuarioIdDTO {
    @NotNull
    private UUID id;
}
