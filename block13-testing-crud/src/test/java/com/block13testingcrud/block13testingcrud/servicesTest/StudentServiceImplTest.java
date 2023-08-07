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
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StudentServiceImplTest {

    @InjectMocks
    @Autowired
    private StudentServiceImpl studentService;

    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private ProfesorRepository profesorRepository;
    @MockBean
    private PersonaRepository personaRepository;
    @MockBean
    private StudentMapper studentMapper;
    @MockBean
    private ProfesorEstudianteRepository profesorEstudianteRepository;
    @MockBean
    private AsignaturaRepository asignaturaRepository;
    @MockBean
    private PersonaService personaService;
    @MockBean
    private ProfesorService profesorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



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


        studentService.deleteStudent(1);


    }
    @Test
    public void testGetStudentFullDetails() {
        // Crear datos ficticios
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setIdStudent(1);


        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(2);


        // Ensure that the StudentEntity instance has its PersonaEntity set
        studentEntity.setPersona(personaEntity);

        // Configurar las respuestas de los repositorios
        when(studentRepository.findById(1)).thenReturn(Optional.of(studentEntity));
        when(personaRepository.findById(2)).thenReturn(Optional.of(personaEntity));

        // Llamar al método que se está probando
        EstudianteFullOutDto result = studentService.getStudentFullDetails(1);

        // Verificar el resultado
        assertEquals(1, result.getIdStudent().intValue());
        assertEquals(2, result.getIdPersona().intValue());

    }


    @Test
    public void agregarEstudianteTest() {
        // Datos de prueba
        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setIdPersona(1);
        studentInputDto.setIdProfesor(2);

        PersonaEntity personaEntity = new PersonaEntity();
        ProfesorEntity profesorEntity = new ProfesorEntity();
        profesorEntity.setStudents(new ArrayList<>());
        profesorEntity.setProfesorEstudiantes(new HashSet<>());


        StudentEntity studentEntity = new StudentEntity();
        StudentEntity savedStudentEntity = new StudentEntity();

        // Configuraciones mock
        when(personaService.buscarPorId(1)).thenReturn(personaEntity);
        when(profesorRepository.findById(2)).thenReturn(Optional.of(profesorEntity));
        when(studentMapper.toEntity(studentInputDto)).thenReturn(studentEntity);
        when(studentRepository.save(studentEntity)).thenReturn(savedStudentEntity);

        // Este es solo un ejemplo, podrías tener que mockear más métodos según tu implementación real
        when(studentMapper.toDTO(savedStudentEntity)).thenReturn(new StudentInputDto());

        // Llamar al método a probar
        StudentInputDto resultDto = studentService.agregarEstudiante(studentInputDto);

        // Verificar el resultado
        assertNotNull(resultDto);

        // Añadir más aserciones si es necesario
        // Por ejemplo, si quieres comprobar que ciertos métodos fueron llamados, etc.
    }


}