package edu.xanderson.PatoCritico.model.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class UsuarioConfirmacaoEntity {
    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private UsuarioEntity usuario;
    
    @Column(nullable = false)
    private UUID code;
    
    @Column(nullable = false)
    private LocalDate dataExpiracao;
}
