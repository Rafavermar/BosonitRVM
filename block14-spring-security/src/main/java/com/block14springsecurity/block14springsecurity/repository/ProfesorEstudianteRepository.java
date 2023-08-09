package com.block14springsecurity.block14springsecurity.repository;


import com.block14springsecurity.block14springsecurity.entities.ProfesorEstudiante;
import com.block14springsecurity.block14springsecurity.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorEstudianteRepository extends JpaRepository<ProfesorEstudiante, Integer> {
    void deleteByStudent(StudentEntity studentEntity);
    List<ProfesorEstudiante> findByStudent(StudentEntity student);

}
