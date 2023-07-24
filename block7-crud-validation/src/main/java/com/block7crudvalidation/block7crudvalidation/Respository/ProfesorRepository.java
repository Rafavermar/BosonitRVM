package com.block7crudvalidation.block7crudvalidation.Respository;

import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, Integer> {

    ProfesorEntity findByPersona(PersonaEntity personaEntity);
    // Method to find professors by name
    List<ProfesorEntity> findByPersonaName(String name);
}
