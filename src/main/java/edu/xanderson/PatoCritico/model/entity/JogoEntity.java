package edu.xanderson.PatoCritico.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.ClassificacaoEtariaEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class JogoEntity {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String titulo;
    
    private String imagem ;
    
    private LocalDate lançamento;
    
    @Column(nullable = false, length = 8000)
    private String descrição;
    
    @Column(nullable = false)
    private String desenvolvedora;
    
    @Column(nullable = false)
    private String publicadora;
    
    @Enumerated(EnumType.STRING)
    private ClassificacaoEtariaEnum classificacaoEtaria = ClassificacaoEtariaEnum.LIVRE;
    
    @OneToOne
    @JoinColumn(nullable = false)
    private GeneroEntity genero;

    @OneToMany
    private List<TagEntity> tags;

    
    @OneToMany
    @Column(nullable = false)
    private List<PlataformaEntity> plataformas;

    private BigDecimal preco = new BigDecimal(0.0);

    @OneToMany(
        mappedBy = "jogo", 
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<AvaliacaoEntity> avaliacoes;

    @OneToOne
    private UsuarioJogoEntity relacaoJogoUsuario;

    @ManyToOne
    private UsuarioEntity dono;
}
