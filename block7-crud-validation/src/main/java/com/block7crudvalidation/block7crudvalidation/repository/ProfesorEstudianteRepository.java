package com.block7crudvalidation.block7crudvalidation.repository;

import com.block7crudvalidation.block7crudvalidation.entities.ProfesorEstudiante;
import com.block7crudvalidation.block7crudvalidation.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorEstudianteRepository extends JpaRepository<ProfesorEstudiante, Integer> {
    void deleteByStudent(StudentEntity studentEntity);
    List<ProfesorEstudiante> findByStudent(StudentEntity student);

}
