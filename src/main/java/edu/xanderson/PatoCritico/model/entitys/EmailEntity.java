package edu.xanderson.PatoCritico.model.entitys;

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
public class EmailEntity {
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String remetente;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private UsuarioEntity destinatario;
    
    @Column(nullable = false)
    private String asunto;
    
    @Column(nullable = false, length = 8000)
    private String mensagem;
}
