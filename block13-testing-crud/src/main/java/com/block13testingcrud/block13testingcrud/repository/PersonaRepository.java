package com.block13testingcrud.block13testingcrud.repository;


import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
    Optional<PersonaEntity> findByUsuario(String usuario);
}
