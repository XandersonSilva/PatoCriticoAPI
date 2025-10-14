package edu.xanderson.PatoCritico.model.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.PatoCritico.model.entitys.UsuarioJogoEntity;

public interface UsuarioJogoRepository extends JpaRepository<UsuarioJogoEntity, UUID>{
    UsuarioJogoEntity findByDonoIdAndJogoId(UUID usuarioId, UUID jogoId);
    List<UsuarioJogoEntity> findByDonoId(UUID usuarioId);
    
}