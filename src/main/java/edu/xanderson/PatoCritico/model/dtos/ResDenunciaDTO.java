package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.MotivoDenunciaEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResDenunciaDTO {
    private UUID id;
    
    private ReqUsuarioIdDTO denunciante;
    
    private ReqAvaliacaoIdDTO avaliacao;
    
    private MotivoDenunciaEnum motivo;
    
}