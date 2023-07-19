package com.block7crudvalidation.block7crudvalidation.Respository;

import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
    Optional<PersonaEntity> findByUsuario(String usuario);
}
