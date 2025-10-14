package edu.xanderson.PatoCritico.model.entitys;

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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { 
           @UniqueConstraint(
               name = "uk_usuario_jogo",
               columnNames = {"dono_id", "jogo_id"}
           ) 
       }
)
public class UsuarioJogoEntity {

    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "dono_id", nullable = false)
    private UsuarioEntity dono;
    
    @ManyToOne
    @JoinColumn(name = "jogo_id", nullable = false)
    private JogoEntity jogo;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusJogoEnum status;
}
