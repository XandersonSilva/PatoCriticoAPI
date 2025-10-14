package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUsuarioRecuperacaoSenhaDTO {
    @NotBlank
    private String novaSenha;
    
    @NotNull
    private UUID token_link;
}
