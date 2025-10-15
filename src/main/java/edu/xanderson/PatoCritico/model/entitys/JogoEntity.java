package edu.xanderson.PatoCritico.model.entitys;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Formula;

import edu.xanderson.PatoCritico.model.enums.ClassificacaoEtariaEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    
    private LocalDateTime lançamento;
    
    @Column(nullable = false, length = 8000)
    private String descrição;
    
    @Column(nullable = false)
    private String desenvolvedora;
    
    @Column(nullable = false)
    private String publicadora;
    
    @Enumerated(EnumType.STRING)
    private ClassificacaoEtariaEnum classificacaoEtaria = ClassificacaoEtariaEnum.LIVRE;
    
    @OneToOne
    private GeneroEntity genero;

    @OneToMany
    private List<TagEntity> tags;

    
    @OneToMany
    private List<PlataformaEntity> plataformas;

    private BigDecimal preco = new BigDecimal(0.0);

    @OneToMany(
        mappedBy = "jogo", 
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    private List<AvaliacaoEntity> avaliacoes;

    @Formula("(SELECT COALESCE(AVG(a.nota), 0.0) FROM avaliacao_entity a WHERE a.jogo_id = id)")
    private double notaMedia;

    @OneToOne
    private UsuarioJogoEntity relacaoJogoUsuario;

    @ManyToOne
    private UsuarioEntity dono;
}
