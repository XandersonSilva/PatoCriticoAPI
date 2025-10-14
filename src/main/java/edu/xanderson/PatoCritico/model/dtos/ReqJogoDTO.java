package edu.xanderson.PatoCritico.model.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.ClassificacaoEtariaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqJogoDTO {
    private UUID id;
    
    @NotBlank
    private String titulo;
    
    private String imagem;
    
    @NotNull
    private LocalDateTime lançamento;
    
    @NotBlank
    private String descrição;
    
    @NotBlank
    private String desenvolvedora;
    
    @NotBlank
    private String publicadora;
    
    private ClassificacaoEtariaEnum classificacaoEtaria;

    private BigDecimal preco;

}