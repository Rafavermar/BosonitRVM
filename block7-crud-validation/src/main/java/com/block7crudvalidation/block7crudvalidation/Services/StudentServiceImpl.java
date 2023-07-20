package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.Respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl {

    @Autowired
    private StudentRepository studentRepository;



    public StudentEntity saveStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
