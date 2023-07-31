package com.block12criteriabuilder.block12criteriabuilder.repository;

import com.block12criteriabuilder.block12criteriabuilder.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
    Optional<PersonaEntity> findByUsuario(String usuario);
}
