package edu.xanderson.PatoCritico.model.entity;

import java.util.UUID;

import edu.xanderson.PatoCritico.model.enums.TagEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TagEntity {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private TagEnum tag;
}
