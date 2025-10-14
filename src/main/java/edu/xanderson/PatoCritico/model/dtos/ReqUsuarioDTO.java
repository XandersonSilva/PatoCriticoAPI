package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUsuarioDTO {

    private UUID id;
    
    private String nome;
    
    private String imagem;
    
    private String email;
    
    private String senha;
}