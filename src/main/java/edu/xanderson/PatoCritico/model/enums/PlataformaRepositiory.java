package edu.xanderson.PatoCritico.model.enums;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.PatoCritico.model.entitys.PlataformaEntity;

public interface PlataformaRepositiory extends JpaRepository<PlataformaEntity, UUID>{
    PlataformaEntity findByPlataforma(PlataformaEnum plataforma);
    
}
