package edu.xanderson.PatoCritico.model.dtos;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.TagEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqTagDTO {
    private UUID id;

    private TagEnum tag;
}