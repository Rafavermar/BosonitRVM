package com.block10dockerizeapp.block10dockerizeapp.repository;


import com.block10dockerizeapp.block10dockerizeapp.entities.PersonaEntity;
import com.block10dockerizeapp.block10dockerizeapp.entities.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, Integer> {

    ProfesorEntity findByPersona(PersonaEntity personaEntity);
    // Method to find professors by name
    List<ProfesorEntity> findByPersonaName(String name);
}
