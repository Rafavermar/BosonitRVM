package com.block13testingcrud.block13testingcrud.servicesTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.block13testingcrud.block13testingcrud.dto.input.StudentInputDto;
import com.block13testingcrud.block13testingcrud.dto.output.EstudianteFullOutDto;
import com.block13testingcrud.block13testingcrud.dto.output.EstudianteSimpleOutDto;
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

import org.mockito.Mockito;
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
        studentEntity.setNumHoursWeek(20);
        studentEntity.setComments("Estudiante aplicado");

        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(2);
        personaEntity.setUsuario("user1");
        personaEntity.setName("John");
        personaEntity.setSurname("Doe");
        personaEntity.setCompanyEmail("john.doe@company.com");
        personaEntity.setPersonalEmail("john.doe@gmail.com");
        personaEntity.setCity("New York");
        personaEntity.setActive(true);
        personaEntity.setCreatedDate(new Date());
        personaEntity.setImageUrl("https://example.com/johndoe.jpg");
        personaEntity.setTerminationDate(null);

        // Ensure that the StudentEntity instance has its PersonaEntity set
        studentEntity.setPersona(personaEntity);

        // Configurar las respuestas de los repositorios
        when(studentRepository.findById(1)).thenReturn(Optional.of(studentEntity));
        when(personaRepository.findById(2)).thenReturn(Optional.of(personaEntity));

        // Llamar al método que se está probando
        EstudianteFullOutDto result = studentService.getStudentFullDetails(1);

        // Verificar el resultado
        assertEquals(1, result.getIdStudent().intValue());
        assertEquals(20, result.getNumHoursWeek());
        assertEquals("Estudiante aplicado", result.getComments());
        assertEquals(2, result.getIdPersona().intValue());
        assertEquals("user1", result.getUsuario());
        assertEquals("John", result.getName());
        assertEquals("Doe", result.getSurname());
        assertEquals("john.doe@company.com", result.getCompanyEmail());
        assertEquals("john.doe@gmail.com", result.getPersonalEmail());
        assertEquals("New York", result.getCity());
        assertTrue(result.isActive());
        assertNotNull(result.getCreatedDate());
        assertEquals("https://example.com/johndoe.jpg", result.getImageUrl());
        assertNull(result.getTerminationDate());
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
    @Test
    void testGetStudentsDTOByName() {
        // Arrange
        String name = "John";
        List<StudentEntity> mockEntities = Arrays.asList(new StudentEntity());
        List<StudentInputDto> mockDtos = Arrays.asList(new StudentInputDto());

        when(studentRepository.findByPersonaName(name)).thenReturn(mockEntities);
        when(studentMapper.toDTO(any(StudentEntity.class))).thenReturn(mockDtos.get(0));

        // Act
        List<StudentInputDto> returnedDtos = studentService.getStudentsDTOByName(name);

        // Assert
        assertNotNull(returnedDtos);
        assertEquals(mockDtos.size(), returnedDtos.size());

    }


    @Test
    void testGetStudentFullDetailsByName() {
        // Arrange
        String name = "John";
        StudentEntity mockStudentEntity = new StudentEntity();
        PersonaEntity mockPersona = new PersonaEntity();
        mockStudentEntity.setPersona(mockPersona);

        List<StudentEntity> mockEntities = Arrays.asList(mockStudentEntity);
        EstudianteFullOutDto mockDto = new EstudianteFullOutDto();

        when(studentRepository.findByPersona_Name(name)).thenReturn(mockEntities);


        // Act
        List<EstudianteFullOutDto> returnedDtos = studentService.getStudentFullDetailsByName(name);

        // Assert
        assertNotNull(returnedDtos);
        assertEquals(1, returnedDtos.size());
    }


    @Test
    void testGetAllStudentsSimpleDetails() {
        // Arrange
        StudentEntity mockEntity1 = new StudentEntity();
        mockEntity1.setIdStudent(1);
        mockEntity1.setNumHoursWeek(20);
        mockEntity1.setComments("Test comments 1");

        StudentEntity mockEntity2 = new StudentEntity();
        mockEntity2.setIdStudent(2);
        mockEntity2.setNumHoursWeek(25);
        mockEntity2.setComments("Test comments 2");

        when(studentRepository.findAll()).thenReturn(Arrays.asList(mockEntity1, mockEntity2));

        // Act
        List<EstudianteSimpleOutDto> resultDtos = studentService.getAllStudentsSimpleDetails();

        // Assert
        assertEquals(2, resultDtos.size());

        EstudianteSimpleOutDto resultDto1 = resultDtos.get(0);
        assertEquals(mockEntity1.getIdStudent(), resultDto1.getIdStudent());
        assertEquals(mockEntity1.getNumHoursWeek(), resultDto1.getNumHoursWeek());
        assertEquals(mockEntity1.getComments(), resultDto1.getComments());

        EstudianteSimpleOutDto resultDto2 = resultDtos.get(1);
        assertEquals(mockEntity2.getIdStudent(), resultDto2.getIdStudent());
        assertEquals(mockEntity2.getNumHoursWeek(), resultDto2.getNumHoursWeek());
        assertEquals(mockEntity2.getComments(), resultDto2.getComments());
    }

    @Test
    void testAsignarAsignaturasStudent() {
        // Arrange
        Integer mockStudentId = 1;
        List<Integer> mockAsignaturasIds = Arrays.asList(1, 2);

        StudentEntity mockStudent = mock(StudentEntity.class);
        AsignaturaEntity mockAsignatura1 = mock(AsignaturaEntity.class);
        AsignaturaEntity mockAsignatura2 = mock(AsignaturaEntity.class);
        List<AsignaturaEntity> mockAsignaturasList = Arrays.asList(mockAsignatura1, mockAsignatura2);

        when(studentRepository.findById(mockStudentId)).thenReturn(Optional.of(mockStudent));
        when(asignaturaRepository.findAllById(mockAsignaturasIds)).thenReturn(mockAsignaturasList);

        // Act
        studentService.asignarAsignaturasStudent(mockStudentId, mockAsignaturasIds);

        // Assert
        verify(mockStudent).getAsignaturas(); // Verifica que se llamó al método
        verify(studentRepository).save(mockStudent);
    }

    @Test
    void testDesasignarAsignaturasStudent() {
        // Arrange
        Integer mockStudentId = 1;
        List<Integer> mockAsignaturasIds = Arrays.asList(1, 2);

        StudentEntity mockStudent = mock(StudentEntity.class);
        AsignaturaEntity mockAsignatura1 = mock(AsignaturaEntity.class);
        AsignaturaEntity mockAsignatura2 = mock(AsignaturaEntity.class);
        List<AsignaturaEntity> mockAsignaturasList = Arrays.asList(mockAsignatura1, mockAsignatura2);

        when(studentRepository.findById(mockStudentId)).thenReturn(Optional.of(mockStudent));
        when(asignaturaRepository.findAllById(mockAsignaturasIds)).thenReturn(mockAsignaturasList);

        // Act
        studentService.desasignarAsignaturasStudent(mockStudentId, mockAsignaturasIds);

        // Assert
        verify(mockStudent).getAsignaturas();
        verify(studentRepository).save(mockStudent);
    }

    @Test
    public void testGetStudentDTOById_validId_returnsDto() {

        Integer id = 1;
        StudentEntity mockEntity = new StudentEntity();
        mockEntity.setIdStudent(1);


        StudentInputDto mockDto = new StudentInputDto();
        mockDto.setIdStudent(1);


        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.of(mockEntity));
        Mockito.when(studentMapper.toDTO(mockEntity)).thenReturn(mockDto);


        StudentInputDto result = studentService.getStudentDTOById(id);


        assertNotNull(result);
        assertEquals(mockDto, result);
    }




    @Test
    void testGetStudentDTOById() {
        // Arrange
        Integer mockStudentId = 1;

        // Creating mocks
        StudentEntity mockStudentEntity = mock(StudentEntity.class);
        StudentInputDto mockStudentInputDto = mock(StudentInputDto.class);

        // Assuming you have a mocked repository or service that internally uses findById
        when(studentRepository.findById(mockStudentId)).thenReturn(Optional.of(mockStudentEntity));
        when(studentMapper.toDTO(mockStudentEntity)).thenReturn(mockStudentInputDto);

        // Act
        StudentInputDto result = studentService.getStudentDTOById(mockStudentId);

        // Assert
        assertNotNull(result);
        assertEquals(mockStudentInputDto, result);
    }


    @Test
    void testGetStudentDTOByIdWhenStudentNotFound() {
        // Arrange
        Integer mockStudentId = 1;

        // Stubbing the method to throw EntityNotFoundException
        when(studentService.getStudentById(mockStudentId)).thenThrow(new EntityNotFoundException(mockStudentId.toString()));

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> {
            studentService.getStudentDTOById(mockStudentId);
        });
    }

    /**

     * Test para {@link StudentServiceImpl#convertToSimpleDto(StudentEntity)}.
     *
     * <p>
     * Aunque generalmente no es una buena práctica testear métodos privados directamente,
     * en este caso particular, hemos optado por hacerlo debido a:
     * </p>
     *
     * <ul>
     *   <li>La lógica dentro del método es crítica para la funcionalidad de varios métodos públicos.</li>
     *   <li>Queremos asegurarnos de que cualquier cambio futuro en este método (incluso menores)
     *       estén completamente probados, sin la necesidad de volver a testear todos los métodos públicos
     *       que dependen de él.</li>
     *   <li>La lógica de este método podría ser intrincada o no trivial, justificando su prueba unitaria directa.</li>
     * </ul>
     *
     * <p>
     * Para lograr esto, utilizamos Reflection de Java para acceder al método privado.
     * Aunque este enfoque rompe el principio de encapsulamiento, es un compromiso consciente para asegurar
     * la robustez y mantenibilidad de la base de código.
     * </p>

    @Test
    public void testConvertToSimpleDto() throws Exception {
        // Given
        StudentEntity mockEntity = new StudentEntity();
        mockEntity.setIdStudent(1);
        mockEntity.setNumHoursWeek(20);
        mockEntity.setComments("Some comments");

        // Use reflection to invoke the private method
        Method convertMethod = StudentServiceImpl.class.getDeclaredMethod("convertToSimpleDto", StudentEntity.class);
        convertMethod.setAccessible(true);

        // When
        EstudianteSimpleOutDto result = (EstudianteSimpleOutDto) convertMethod.invoke(studentServiceImplInstance, mockEntity);

        // Then
        assertNotNull(result);
        assertEquals(Integer.valueOf(1), result.getIdStudent());
        assertEquals(Integer.valueOf(20), result.getNumHoursWeek());
        assertEquals("Some comments", result.getComments());
    }
**/



}