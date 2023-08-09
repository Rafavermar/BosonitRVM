package com.block14springsecurity.block14springsecurity.repository;



import com.block14springsecurity.block14springsecurity.entities.PersonaEntity;
import com.block14springsecurity.block14springsecurity.entities.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, Integer> {

    ProfesorEntity findByPersona(PersonaEntity personaEntity);

    List<ProfesorEntity> findByPersonaName(String name);
}
