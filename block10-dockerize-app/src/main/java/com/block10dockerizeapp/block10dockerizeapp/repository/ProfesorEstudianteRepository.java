package com.block10dockerizeapp.block10dockerizeapp.repository;


import com.block10dockerizeapp.block10dockerizeapp.entities.ProfesorEstudiante;
import com.block10dockerizeapp.block10dockerizeapp.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorEstudianteRepository extends JpaRepository<ProfesorEstudiante, Integer> {
    void deleteByStudent(StudentEntity studentEntity);
    List<ProfesorEstudiante> findByStudent(StudentEntity student);

}
