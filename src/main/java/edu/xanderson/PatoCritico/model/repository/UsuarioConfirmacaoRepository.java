package edu.xanderson.PatoCritico.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.PatoCritico.model.entitys.UsuarioConfirmacaoEntity;

public interface UsuarioConfirmacaoRepository extends JpaRepository<UsuarioConfirmacaoEntity, UUID>{
    UsuarioConfirmacaoEntity findByUsuarioId(UUID usuarioId);
    UsuarioConfirmacaoEntity findByCode(UUID code);
    
}