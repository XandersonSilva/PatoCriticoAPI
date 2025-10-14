package edu.xanderson.PatoCritico.model.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.PatoCritico.model.entitys.VotoEntity;

public interface VotoRepository extends JpaRepository<VotoEntity, UUID>{
    List<VotoEntity> findByAvaliacaoId(UUID avaliacaoId);
    
}