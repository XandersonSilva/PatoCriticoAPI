package edu.xanderson.PatoCritico.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqRegistrarUsuarioDTO {
    
    @NotBlank
    String nome;
    
    @Email
    String email;
    
    @NotBlank
    String senha;

}
