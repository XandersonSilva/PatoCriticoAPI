package edu.xanderson.PatoCritico.model.entitys;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UsuarioRecuperacaoSenhaEntity {
    @Id
    @GeneratedValue
    UUID id;

    @OneToOne
    @JoinColumn(nullable = false)
    private UsuarioEntity usuario;

    @Column(nullable = false)
    private UUID token;

    @Column(nullable = false)
    private LocalDateTime expiracao;
}
