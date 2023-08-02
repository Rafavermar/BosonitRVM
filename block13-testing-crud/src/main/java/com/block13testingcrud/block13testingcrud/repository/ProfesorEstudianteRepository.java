package com.block13testingcrud.block13testingcrud.repository;

import com.block13testingcrud.block13testingcrud.entities.ProfesorEstudiante;
import com.block13testingcrud.block13testingcrud.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorEstudianteRepository extends JpaRepository<ProfesorEstudiante, Integer> {
    void deleteByStudent(StudentEntity studentEntity);
    List<ProfesorEstudiante> findByStudent(StudentEntity student);

}
