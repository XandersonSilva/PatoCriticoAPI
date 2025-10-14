package edu.xanderson.PatoCritico.model.entitys;

import java.time.LocalDateTime;
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
    @JoinColumn(nullable = false, unique = true)
    private UsuarioEntity usuario;
    
    @Column(nullable = false)
    private UUID code;
    
    @Column(nullable = false)
    private LocalDateTime dataExpiracao;
}
