package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.DTO.EstudianteFullDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;

import java.util.List;

public interface StudentService {

    StudentEntity saveStudent(StudentEntity student);

    List<StudentEntity> getAllStudents();

    StudentEntity getStudentById(Integer id);

    void deleteStudent(Integer id);

    EstudianteFullDTO getStudentFullDetails(Integer id);
}
