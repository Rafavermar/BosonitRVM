package com.block10dockerizeapp.block10dockerizeapp.services;


import com.block10dockerizeapp.block10dockerizeapp.dto.input.StudentInputDto;
import com.block10dockerizeapp.block10dockerizeapp.dto.output.EstudianteFullOutDto;
import com.block10dockerizeapp.block10dockerizeapp.dto.output.EstudianteSimpleOutDto;
import com.block10dockerizeapp.block10dockerizeapp.entities.StudentEntity;

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
