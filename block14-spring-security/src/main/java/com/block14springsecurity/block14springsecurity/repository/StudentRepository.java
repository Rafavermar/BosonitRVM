package com.block14springsecurity.block14springsecurity.repository;



import com.block14springsecurity.block14springsecurity.entities.PersonaEntity;
import com.block14springsecurity.block14springsecurity.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    StudentEntity findByPersona(PersonaEntity personaEntity);
    Optional<StudentEntity> findByIdStudent(Integer idStudent);

    // New method to find student by name, now returning a List
    List<StudentEntity> findByPersona_Name(String name);

    // New method to find student by persona name, now returning a List
    List<StudentEntity> findByPersonaName(String name);


}
