package edu.xanderson.PatoCritico.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.PatoCritico.model.entitys.PlataformaEntity;

public interface PlataformaRepository extends JpaRepository<PlataformaEntity, UUID>{
    
}
