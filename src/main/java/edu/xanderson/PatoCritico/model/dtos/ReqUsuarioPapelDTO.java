package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.PapelEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUsuarioPapelDTO {
    @NotNull
    UUID usuarioId;

    @NotNull
    PapelEnum papel;
}
