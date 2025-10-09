package edu.xanderson.PatoCritico.model.entity;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.StatusJogoEnum;
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
public class UsuarioJogoEntity {

    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private UsuarioEntity dono;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private JogoEntity jogo;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusJogoEnum status;
}
