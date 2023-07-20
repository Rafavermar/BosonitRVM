package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;

import java.util.List;

public interface StudentService {

    StudentEntity saveStudent(StudentEntity student);

    List<StudentEntity> getAllStudents();

    StudentEntity getStudentById(Long id);

    void deleteStudent(Long id);


}
