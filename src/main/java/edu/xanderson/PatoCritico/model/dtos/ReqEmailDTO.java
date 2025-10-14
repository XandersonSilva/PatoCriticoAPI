package edu.xanderson.PatoCritico.model.dtos;

import edu.xanderson.PatoCritico.model.entitys.UsuarioEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqEmailDTO {

    private UsuarioEntity destinatario;
    
    private String asunto;
    
    private String mensagem;
}