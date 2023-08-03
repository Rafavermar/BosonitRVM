package com.block13testingcrud.block13testingcrud.servicesTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.block13testingcrud.block13testingcrud.entities.StudentEntity;
import com.block13testingcrud.block13testingcrud.exception.EntityNotFoundException;
import com.block13testingcrud.block13testingcrud.mapper.StudentMapper;
import com.block13testingcrud.block13testingcrud.repository.PersonaRepository;
import com.block13testingcrud.block13testingcrud.repository.ProfesorEstudianteRepository;
import com.block13testingcrud.block13testingcrud.repository.ProfesorRepository;
import com.block13testingcrud.block13testingcrud.repository.StudentRepository;
import com.block13testingcrud.block13testingcrud.services.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private ProfesorEstudianteRepository profesorEstudianteRepository;



    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void testSaveStudent() {
        StudentEntity studentEntity = new StudentEntity();


        when(studentRepository.save(studentEntity)).thenReturn(studentEntity);

        StudentEntity result = studentService.saveStudent(studentEntity);
        assertEquals(studentEntity, result);
    }

    @Test
    public void testGetAllStudents() {
        StudentEntity studentEntity1 = new StudentEntity();
        StudentEntity studentEntity2 = new StudentEntity();


        when(studentRepository.findAll()).thenReturn(Arrays.asList(studentEntity1, studentEntity2));

        List<StudentEntity> result = studentService.getAllStudents();
        assertEquals(2, result.size());
        assertEquals(studentEntity1, result.get(0));
        assertEquals(studentEntity2, result.get(1));
    }

    @Test
    public void testGetStudentById() {
        StudentEntity studentEntity = new StudentEntity();
        int id = 1;

        when(studentRepository.findById(id)).thenReturn(Optional.of(studentEntity));

        StudentEntity result = studentService.getStudentById(id);
        assertEquals(studentEntity, result);
    }

    @Test
    public void testDeleteStudent() {
        int idStudent = 1;
        StudentEntity studentEntity = new StudentEntity();


        when(studentRepository.findByIdStudent(idStudent)).thenReturn(Optional.of(studentEntity));
        doNothing().when(profesorEstudianteRepository).deleteByStudent(studentEntity);

        assertDoesNotThrow(() -> studentService.deleteStudent(idStudent));
        verify(studentRepository).delete(studentEntity);
    }

    @Test
    public void testDeleteStudentNotFound() {
        int idStudent = 1;

        when(studentRepository.findByIdStudent(idStudent)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> studentService.deleteStudent(idStudent));
    }



}
