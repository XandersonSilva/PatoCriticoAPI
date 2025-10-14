package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.MotivoDenunciaEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqDenunciaDTO {

    private UUID id;
    
    private ReqUsuarioIdDTO denunciante;
    
    @NotNull
    private ReqAvaliacaoIdDTO avaliacao;
    
    @NotNull
    private MotivoDenunciaEnum motivo;
}