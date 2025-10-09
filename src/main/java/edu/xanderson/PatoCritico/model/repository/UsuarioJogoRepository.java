package edu.xanderson.PatoCritico.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.PatoCritico.model.entity.UsuarioJogoEntity;

public interface UsuarioJogoRepository extends JpaRepository<UsuarioJogoEntity, UUID>{

    
}