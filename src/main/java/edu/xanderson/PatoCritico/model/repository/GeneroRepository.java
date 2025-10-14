package edu.xanderson.PatoCritico.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.PatoCritico.model.entitys.GeneroEntity;

public interface GeneroRepository extends JpaRepository<GeneroEntity, UUID>{
    
}
