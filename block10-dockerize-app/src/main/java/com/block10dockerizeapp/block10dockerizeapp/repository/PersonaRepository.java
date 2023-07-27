package com.block10dockerizeapp.block10dockerizeapp.repository;


import com.block10dockerizeapp.block10dockerizeapp.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
    Optional<PersonaEntity> findByUsuario(String usuario);
}
