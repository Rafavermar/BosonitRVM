package com.block12criteriabuilder.block12criteriabuilder.repository;


import com.block12criteriabuilder.block12criteriabuilder.entities.ProfesorEstudiante;
import com.block12criteriabuilder.block12criteriabuilder.entities.StudentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorEstudianteRepository extends JpaRepository<ProfesorEstudiante, Integer> {
    void deleteByStudent(StudentEntity studentEntity);
    List<ProfesorEstudiante> findByStudent(StudentEntity student);

}
