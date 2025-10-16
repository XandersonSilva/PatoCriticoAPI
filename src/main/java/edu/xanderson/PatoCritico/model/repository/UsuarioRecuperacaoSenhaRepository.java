package edu.xanderson.PatoCritico.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


import edu.xanderson.PatoCritico.model.entitys.UsuarioRecuperacaoSenhaEntity;

public interface UsuarioRecuperacaoSenhaRepository extends JpaRepository<UsuarioRecuperacaoSenhaEntity, UUID>{
    UsuarioRecuperacaoSenhaEntity findByUsuarioId(UUID ususrio);
    UsuarioRecuperacaoSenhaEntity findByToken(UUID token);
}
