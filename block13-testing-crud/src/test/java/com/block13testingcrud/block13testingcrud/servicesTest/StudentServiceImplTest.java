package com.block13testingcrud.block13testingcrud.servicesTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.block13testingcrud.block13testingcrud.dto.input.StudentInputDto;
import com.block13testingcrud.block13testingcrud.dto.output.EstudianteFullOutDto;
import com.block13testingcrud.block13testingcrud.entities.*;
import com.block13testingcrud.block13testingcrud.exception.EntityNotFoundException;
import com.block13testingcrud.block13testingcrud.mapper.StudentMapper;
import com.block13testingcrud.block13testingcrud.repository.*;
import com.block13testingcrud.block13testingcrud.services.PersonaService;
import com.block13testingcrud.block13testingcrud.services.ProfesorService;
import com.block13testingcrud.block13testingcrud.services.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

public class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

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
    @Mock
    private AsignaturaRepository asignaturaRepository;
    @Mock
    private PersonaService personaService;
    @Mock
    private ProfesorService profesorService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }







/*    @Test
    public void testDeleteStudent() {
        Integer id = 1;
        StudentEntity student = new StudentEntity();
        when(studentRepository.findByIdStudent(id)).thenReturn(Optional.of(student));

        studentService.deleteStudent(id);

        verify(profesorEstudianteRepository, times(1)).deleteByStudent(student);
        verify(studentRepository, times(1)).delete(student);
    }*/


    @Test
    public void testDeleteStudentNotFound() {
        Integer id = 1;
        when(studentRepository.findByIdStudent(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> studentService.deleteStudent(id));
    }



    @Test
    public void testDeleteStudentAsignaturasHandling() {
        Integer id = 1;
        StudentEntity student = new StudentEntity();
        AsignaturaEntity asignatura = new AsignaturaEntity();
        asignatura.getStudents().add(student);
        student.getAsignaturas().add(asignatura);

        when(studentRepository.findByIdStudent(id)).thenReturn(Optional.of(student));

        studentService.deleteStudent(id);

        assertTrue(asignatura.getStudents().isEmpty());
        assertTrue(student.getAsignaturas().isEmpty());
        verify(studentRepository, times(1)).save(student); // Verificar que se guarda el estudiante desvinculado
        verify(studentRepository, times(1)).delete(student);
    }

    @Test
    public void testDeleteStudentProfesorEstudiantesHandling() {
        Integer id = 1;
        StudentEntity student = new StudentEntity();
        ProfesorEstudiante profesorEstudiante = new ProfesorEstudiante();
        List<ProfesorEstudiante> profesorEstudiantes = List.of(profesorEstudiante);

        when(studentRepository.findByIdStudent(id)).thenReturn(Optional.of(student));
        when(profesorEstudianteRepository.findByStudent(student)).thenReturn(profesorEstudiantes);

        studentService.deleteStudent(id);

        verify(profesorEstudianteRepository, times(1)).deleteByStudent(student);
        verify(studentRepository, times(1)).delete(student);
    }


    @Test
    public void testStudentEntityGettersAndSetters() {
        StudentEntity student = new StudentEntity();
        PersonaEntity persona = new PersonaEntity();
        persona.setName("John Doe");


        student.setIdStudent(1);
        student.setPersona(persona);

        assertEquals(1, student.getIdStudent());
        assertEquals("John Doe", student.getPersona().getName());

    }
    @Test
    public void testSaveStudent() {
        StudentEntity student = new StudentEntity();
        when(studentRepository.save(student)).thenReturn(student);

        StudentEntity result = studentService.saveStudent(student);
        assertEquals(result, student);
    }

    @Test
    public void testGetAllStudents() {
        List<StudentEntity> students = Arrays.asList(new StudentEntity());
        when(studentRepository.findAll()).thenReturn(students);

        List<StudentEntity> result = studentService.getAllStudents();
        assertEquals(result, students);
    }

    @Test
    public void testGetStudentById() {
        StudentEntity student = new StudentEntity();
        student.setIdStudent(1);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        StudentEntity result = studentService.getStudentById(1);
        assertEquals(result, student);
    }

    @Test
    public void testDeleteStudent() {
        StudentEntity student = new StudentEntity();
        student.setIdStudent(1);
        when(studentRepository.findByIdStudent(1)).thenReturn(Optional.of(student));

        // Agrega más comportamientos simulados según sea necesario

        studentService.deleteStudent(1);

        // Agrega más aserciones según sea necesario
    }
    @Test
    public void testGetStudentFullDetails() {
        // Crear datos ficticios
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setIdStudent(1);
        // (Establecer más propiedades aquí)

        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(2);
        // (Establecer más propiedades aquí)

        // Configurar las respuestas de los repositorios
        when(studentRepository.findById(1)).thenReturn(Optional.of(studentEntity));
        when(personaRepository.findById(2)).thenReturn(Optional.of(personaEntity));

        // Llamar al método que se está probando
        EstudianteFullOutDto result = studentService.getStudentFullDetails(1);

        // Verificar el resultado
        assertEquals(1, result.getIdStudent().intValue());
        assertEquals(2, result.getIdPersona().intValue());
        // (Verificar más propiedades aquí)
    }

    @Test
    public void testAgregarEstudiante() {
        // Crear datos ficticios
        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setIdPersona(1);
        studentInputDto.setIdProfesor(2);
        // (Establecer más propiedades aquí)

        PersonaEntity personaEntity = new PersonaEntity();
        // (Establecer propiedades aquí)

        ProfesorEntity profesorEntity = new ProfesorEntity();
        // (Establecer propiedades aquí)

        // Configurar las respuestas de los servicios/repositorios
        when(personaService.buscarPorId(1)).thenReturn(personaEntity);
        when(profesorRepository.findById(2)).thenReturn(Optional.of(profesorEntity));
        // (Configurar más respuestas aquí)

        // Llamar al método que se está probando
        StudentInputDto result = studentService.agregarEstudiante(studentInputDto);

        // Verificar el resultado
        // (Asegúrate de verificar las propiedades y las llamadas esperadas)
    }

}