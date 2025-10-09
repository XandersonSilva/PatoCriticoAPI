package edu.xanderson.PatoCritico.model.enums;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.PatoCritico.model.entity.PlataformaEntity;

public interface PlataformaRepositiory extends JpaRepository<PlataformaEntity, UUID>{
    
}
