package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.entitys.AvaliacaoEntity;
import edu.xanderson.PatoCritico.model.enums.TipoVotoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqVotoDTO {
    
    private UUID id;

    @NotNull
    private TipoVotoEnum tipo;

    private ReqUsuarioIdDTO votante;

    @NotNull
    private AvaliacaoEntity avaliacao;
}