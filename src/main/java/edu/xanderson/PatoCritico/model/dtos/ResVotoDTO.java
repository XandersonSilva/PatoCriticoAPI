package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.TipoVotoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResVotoDTO {

    private UUID id;

    private TipoVotoEnum tipo;
}