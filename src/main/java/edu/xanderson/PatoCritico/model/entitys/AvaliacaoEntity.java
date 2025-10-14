package edu.xanderson.PatoCritico.model.entitys;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class AvaliacaoEntity {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne        
    private UsuarioEntity autor;
    
    @Column(nullable = false)    
    private String titulo;
    
    @Column(nullable = false, length = 8000)    
    private String descricao;
    
    @Column(nullable = false)    
    private Integer nota;
    
    @Column(nullable = false)    
    private LocalDateTime dataPostagem = LocalDateTime.now();

    @OneToMany(
        mappedBy = "avaliacao", 
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<VotoEntity> votos;

    @OneToMany(
        mappedBy = "avaliacao", 
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<DenunciaEntity> denuncias;

    @ManyToOne
    private JogoEntity jogo;
}
