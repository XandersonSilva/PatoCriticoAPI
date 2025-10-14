package edu.xanderson.PatoCritico.model.dtos;


import java.time.LocalDateTime;
import java.util.UUID;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqAvaliacaoDTO {
    private UUID id;
    
    private ReqUsuarioIdDTO autor;
    
    @NotNull
    private String titulo;
    
    @NotNull
    private String descricao;
    
    @Min(value = 1, message = "A nota deve ser no mínimo 1.")
    @Max(value = 5, message = "A nota deve ser no máximo 5.")
    private Integer nota;
    
    private LocalDateTime dataPostagem = LocalDateTime.now();

    @NotNull
    private ReqJogoIdDTO jogo;
}