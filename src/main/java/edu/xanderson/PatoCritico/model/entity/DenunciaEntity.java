package edu.xanderson.PatoCritico.model.entity;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.MotivoDenunciaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class DenunciaEntity {
    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private UsuarioEntity denunciante;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private AvaliacaoEntity avaliacao;
    
    @Column(nullable = false, length = 8000)
    @Enumerated(EnumType.STRING)
    private MotivoDenunciaEnum motivo;
}
