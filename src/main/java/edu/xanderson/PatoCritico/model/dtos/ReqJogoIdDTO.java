package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqJogoIdDTO {
    @NotNull
    private UUID id;

}