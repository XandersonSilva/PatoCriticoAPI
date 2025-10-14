package edu.xanderson.PatoCritico.model.entitys;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.TipoVotoEnum;

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
public class VotoEntity {
    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne
    @JoinColumn(nullable= false)
    private UsuarioEntity votante;
    
    @ManyToOne
    @JoinColumn(nullable= false)
    private AvaliacaoEntity avaliacao;
    
    @Column(nullable= false)
    @Enumerated(EnumType.STRING)
    private TipoVotoEnum tipo;
}

