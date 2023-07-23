package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.StudentDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Output.EstudianteFullDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;

import java.util.List;

public interface StudentService {

    StudentEntity saveStudent(StudentEntity student);

    List<StudentEntity> getAllStudents();

    StudentEntity getStudentById(Integer id);

    void deleteStudent(Integer id);

    EstudianteFullDTO getStudentFullDetails(Integer id);

    StudentDTO agregarEstudiante(StudentDTO studentDTO);

    // New method to get StudentDTO by id
    StudentDTO getStudentDTOById(Integer id);

    // New method to get StudentDTO by name
    StudentDTO getStudentDTOByName(String name);

    // Métodos para obtener los detalles completos del estudiante, según el ID o el nombre
    EstudianteFullDTO getStudentFullDetailsByName(String name);
}
