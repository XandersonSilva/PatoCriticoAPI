package edu.xanderson.PatoCritico.model.dtos;

import edu.xanderson.PatoCritico.model.enums.PapelEnum;
import edu.xanderson.PatoCritico.model.enums.UsuarioEstadoEnum;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResUsuarioDTO {
    private String nome;
    private String imagem;
    private String email;
    private PapelEnum papel;
    private UsuarioEstadoEnum ususarioEstado;
    
}