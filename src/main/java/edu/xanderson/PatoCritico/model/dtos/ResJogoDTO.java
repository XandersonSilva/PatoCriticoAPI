package edu.xanderson.PatoCritico.model.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

import edu.xanderson.PatoCritico.model.entitys.PlataformaEntity;
import edu.xanderson.PatoCritico.model.enums.ClassificacaoEtariaEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResJogoDTO {
    private UUID id;
    
    private String titulo;
    
    private String imagem;
    
    private LocalDateTime lançamento;
    
    private String descrição;
    
    private String desenvolvedora;
    
    private String publicadora;
    
    private ClassificacaoEtariaEnum classificacaoEtaria;

    private List<PlataformaEntity> plataformas;

    private BigDecimal preco;

    private double notaMedia;
    
}
