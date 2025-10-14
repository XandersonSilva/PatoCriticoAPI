package edu.xanderson.PatoCritico.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.PatoCritico.model.entitys.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, UUID>{
    
}
