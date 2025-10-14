package edu.xanderson.PatoCritico.model.entitys;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.xanderson.PatoCritico.model.enums.PapelEnum;
import edu.xanderson.PatoCritico.model.enums.UsuarioEstadoEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UsuarioEntity implements UserDetails{

    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String nome;
    
    private String imagem;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PapelEnum papel = PapelEnum.USUARIO;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UsuarioEstadoEnum ususarioEstado = UsuarioEstadoEnum.PENDENTE;

    @OneToMany(
        mappedBy = "destinatario", 
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<EmailEntity> emails;

    @OneToMany(
        mappedBy = "dono", 
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<JogoEntity> jogos;

    @OneToMany(
        mappedBy = "dono",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<UsuarioJogoEntity> relacaoJogoUsuario;

    @OneToMany(
        mappedBy = "votante",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<VotoEntity> votos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.papel.name()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
