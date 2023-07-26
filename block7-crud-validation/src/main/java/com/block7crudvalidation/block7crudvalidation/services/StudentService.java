package com.block7crudvalidation.block7crudvalidation.services;

import com.block7crudvalidation.block7crudvalidation.dto.input.StudentInputDto;
import com.block7crudvalidation.block7crudvalidation.dto.output.EstudianteFullOutDto;
import com.block7crudvalidation.block7crudvalidation.dto.output.EstudianteSimpleOutDto;
import com.block7crudvalidation.block7crudvalidation.entities.StudentEntity;

import java.util.List;public interface StudentService {

    StudentEntity saveStudent(StudentEntity student);

    List<StudentEntity> getAllStudents();

    List<EstudianteSimpleOutDto> getAllStudentsSimpleDetails();  // Aquí está el nuevo método

    StudentEntity getStudentById(Integer id);

    void deleteStudent(Integer id);

    List<EstudianteFullOutDto> getStudentFullDetails();

    EstudianteFullOutDto getStudentFullDetails(Integer id);

    StudentInputDto agregarEstudiante(StudentInputDto studentInputDto);

    StudentInputDto getStudentDTOById(Integer id);

    List<StudentInputDto> getStudentsDTOByName(String name);

    List<EstudianteFullOutDto> getStudentFullDetailsByName(String name);

    void asignarAsignaturasStudent(Integer idStudent, List<Integer> idsAsignaturas);

    void desasignarAsignaturasStudent(Integer idStudent, List<Integer> idsAsignaturas);
}
