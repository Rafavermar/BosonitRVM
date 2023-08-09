package com.block13testingcrud.block13testingcrud.repository;


import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.entities.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, Integer> {

    ProfesorEntity findByPersona(PersonaEntity personaEntity);

    List<ProfesorEntity> findByPersonaName(String name);
}
