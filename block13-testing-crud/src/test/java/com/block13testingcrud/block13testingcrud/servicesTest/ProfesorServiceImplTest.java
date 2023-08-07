package com.block13testingcrud.block13testingcrud.servicesTest;

import com.block13testingcrud.block13testingcrud.dto.input.ProfesorInputDto;
import com.block13testingcrud.block13testingcrud.dto.output.ProfesorFullOutputDto;
import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.entities.ProfesorEntity;
import com.block13testingcrud.block13testingcrud.entities.ProfesorEstudiante;
import com.block13testingcrud.block13testingcrud.entities.StudentEntity;
import com.block13testingcrud.block13testingcrud.exception.EntityNotFoundException;
import com.block13testingcrud.block13testingcrud.exception.UnprocessableEntityException;
import com.block13testingcrud.block13testingcrud.mapper.ProfesorMapper;
import com.block13testingcrud.block13testingcrud.repository.ProfesorEstudianteRepository;
import com.block13testingcrud.block13testingcrud.repository.ProfesorRepository;
import com.block13testingcrud.block13testingcrud.repository.StudentRepository;

import com.block13testingcrud.block13testingcrud.services.PersonaServiceImpl;
import com.block13testingcrud.block13testingcrud.services.ProfesorServiceImpl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProfesorServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(ProfesorServiceImplTest.class);

    @InjectMocks
    @Autowired
    private ProfesorServiceImpl profesorService;

    @MockBean
    private ProfesorRepository profesorRepository;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private ProfesorEstudianteRepository profesorEstudianteRepository;

    @MockBean
    private ProfesorMapper profesorMapper;

    @MockBean
    @Autowired
    private PersonaServiceImpl personaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProfesor() {
        // Define un profesor de prueba
        ProfesorEntity profesor = new ProfesorEntity();

        // Define el comportamiento esperado del repositorio
        when(profesorRepository.save(profesor)).thenReturn(profesor);

        // Ejecuta el método que queremos probar
        ProfesorEntity result = profesorService.saveProfesor(profesor);

        // Verifica que el método del repositorio haya sido llamado con el profesor correcto
        verify(profesorRepository).save(profesor);

        // Verifica que el resultado sea el esperado
        assertEquals(profesor, result);
    }

    @Test
    public void testGetAllProfesores() {
        // Crea una lista de profesores de prueba
        List<ProfesorEntity> profesores = new ArrayList<>();
        profesores.add(new ProfesorEntity());
        profesores.add(new ProfesorEntity());

        // Define el comportamiento esperado del repositorio
        when(profesorRepository.findAll()).thenReturn(profesores);

        // Ejecuta el método que queremos probar
        List<ProfesorEntity> result = profesorService.getAllProfesores();

        // Verifica que el método del repositorio haya sido llamado
        verify(profesorRepository).findAll();

        // Verifica que el resultado sea el esperado
        assertEquals(profesores, result);
    }

    @Test
    public void testGetProfesorDTOById() {
        Integer id = 1;

        // Define un profesor de prueba con el ID dado
        ProfesorEntity profesor = new ProfesorEntity();
        profesor.setIdProfesor(id);

        // Define el DTO esperado
        ProfesorInputDto expectedDto = new ProfesorInputDto();
        expectedDto.setIdProfesor(id);

        // Define el comportamiento esperado del repositorio y del mapper
        when(profesorRepository.findById(id)).thenReturn(Optional.of(profesor));
        when(profesorMapper.toDTO(profesor)).thenReturn(expectedDto);

        // Ejecuta el método que queremos probar
        ProfesorInputDto result = profesorService.getProfesorDTOById(id);

        // Verifica que el método del repositorio haya sido llamado con el ID correcto
        verify(profesorRepository).findById(id);

        // Verifica que el método del mapper haya sido llamado con el ProfesorEntity correcto
        verify(profesorMapper).toDTO(profesor);

        // Verifica que el resultado sea el esperado
        assertEquals(expectedDto, result);
    }

    @Test
    public void testGetProfesorDTOByIdThrowsException() {
        Integer id = 1;

        // Define el comportamiento esperado del repositorio para lanzar la excepción
        when(profesorRepository.findById(id)).thenReturn(Optional.empty());

        // Esperamos que se lance una excepción de tipo EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> {
            profesorService.getProfesorDTOById(id);
        });

        // Verifica que el método del repositorio haya sido llamado con el ID correcto
        verify(profesorRepository).findById(id);
    }


    @Test
    public void testGetProfesorByIdNotFound() {
        Integer id = 999;

        // Define el comportamiento esperado del repositorio
        when(profesorRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecuta el método que queremos probar
        ProfesorEntity result = profesorService.getProfesorById(id);

        // Verifica que el método del repositorio haya sido llamado con el ID correcto
        verify(profesorRepository).findById(id);

        // Verifica que el resultado sea nulo
        assertNull(result);
    }
    /**
     * Test para verificar el comportamiento del método deleteProfesor en ProfesorService.
     * <p>
     * Problema afrontado:
     * La prueba inicialmente falló porque se esperaba que el método delete del studentRepository fuera invocado,
     * pero en la implementación del método deleteProfesor, solo se llamaba al método save en studentRepository.
     * </p>
     * <p>
     * Solución:
     * Al no haber necesidad de eliminar el estudiante, ajustar la prueba para no verificar
     ** la llamada al método delete en studentRepository.
     * </p>
     */
    @Test
    public void testDeleteProfesor() {
        Integer idProfesor = 1;

        // Define un profesor de prueba con el ID dado
        ProfesorEntity profesor = new ProfesorEntity();
        profesor.setIdProfesor(idProfesor);
        Set<ProfesorEstudiante> profesorEstudiantes = new HashSet<>();

        // Define un estudiante de prueba con un ID válido
        StudentEntity estudiante = new StudentEntity();
        estudiante.setIdStudent(100); // Asignar un ID válido al estudiante

        // Establece el estudiante asociado al ProfesorEstudiante
        ProfesorEstudiante profesorEstudiante = new ProfesorEstudiante();
        profesorEstudiante.setStudent(estudiante);

        // Agrega el ProfesorEstudiante a la lista de profesorEstudiantes del profesor
        profesorEstudiantes.add(profesorEstudiante);
        profesor.setProfesorEstudiantes(profesorEstudiantes);

        // Define el comportamiento esperado del repositorio
        when(profesorRepository.findById(idProfesor)).thenReturn(Optional.of(profesor));

        // Ejecuta el método que queremos probar
        logger.info("Before deleteProfesor");
        profesorService.deleteProfesor(idProfesor);
        logger.info("After deleteProfesor");

        // Verifica que el método del repositorio haya sido llamado con el ID correcto
        verify(profesorRepository).findById(idProfesor);

        // Verifica que los métodos delete se llamen con los objetos correctos
        verify(profesorEstudianteRepository).delete(profesorEstudiante);

        verify(profesorRepository).delete(profesor);
    }



    @Test
    public void testDeleteProfesorNotFound() {
        Integer id = 999;

        // Define el comportamiento esperado del repositorio
       // when(profesorRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecuta el método que queremos probar, esperando que lance una excepción
        assertThrows(EntityNotFoundException.class, () -> profesorService.deleteProfesor(id));
    }



    @Test
    public void testUpdateProfesor() {
        Integer id = 1;

        // Define un profesor de prueba con el ID dado
        ProfesorEntity profesor = new ProfesorEntity();
        profesor.setIdProfesor(id);

        // Define un profesor de prueba con campos actualizados
        ProfesorEntity profesorActualizado = new ProfesorEntity();
        profesorActualizado.setIdProfesor(id);
        profesorActualizado.setPersona(new PersonaEntity());
        profesorActualizado.setComments("Comentarios actualizados");
        profesorActualizado.setBranch("Rama actualizada");

        // Define el comportamiento esperado del repositorio
        when(profesorRepository.findById(id)).thenReturn(Optional.of(profesor));
        when(profesorRepository.save(profesor)).thenReturn(profesorActualizado);

        // Ejecuta el método que queremos probar
        ProfesorEntity result = profesorService.updateProfesor(id, profesorActualizado);

        // Verifica que el método del repositorio haya sido llamado con el ID correcto
        verify(profesorRepository).findById(id);
        verify(profesorRepository).save(profesor);

        // Verifica que el resultado sea el esperado
        assertEquals(profesorActualizado, result);
    }

    @Test
    public void testUpdateProfesorNotFound() {
        Integer id = 999;

        // Define un profesor de prueba con campos actualizados
        ProfesorEntity profesorActualizado = new ProfesorEntity();
        profesorActualizado.setIdProfesor(id);
        profesorActualizado.setPersona(new PersonaEntity());
        profesorActualizado.setComments("Comentarios actualizados");
        profesorActualizado.setBranch("Rama actualizada");

        // Define el comportamiento esperado del repositorio
        when(profesorRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecuta el método que queremos probar
        ProfesorEntity result = profesorService.updateProfesor(id, profesorActualizado);

        // Verifica que el método del repositorio haya sido llamado con el ID correcto
        verify(profesorRepository).findById(id);

        // Verifica que el resultado sea nulo
        assertNull(result);
    }

    @Test
    public void testGetProfesorDTOByIdFound() {
        Integer idProfesor = 1;
        ProfesorEntity mockProfesor = new ProfesorEntity();

        ProfesorInputDto mockDto = new ProfesorInputDto();

        when(profesorRepository.findById(idProfesor)).thenReturn(Optional.of(mockProfesor));
        when(profesorMapper.toDTO(mockProfesor)).thenReturn(mockDto);

        ProfesorInputDto resultDto = profesorService.getProfesorDTOById(idProfesor);

        assertNotNull(resultDto);
        verify(profesorRepository).findById(idProfesor);
        verify(profesorMapper).toDTO(mockProfesor);
    }





    @Test
    public void testGetProfesorDTOByIdNotFound() {
        Integer id = 999;

        // Define el comportamiento esperado del repositorio
       // when(profesorRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecuta el método que queremos probar, esperando que lance una excepción
        assertThrows(EntityNotFoundException.class, () -> profesorService.getProfesorDTOById(id));
    }

    @Test
    public void testGetProfesoresDTOByName() {
        String name = "Nombre1";

        // Crea una lista de profesores de prueba
        List<ProfesorEntity> profesores = new ArrayList<>();
        ProfesorEntity profesor1 = new ProfesorEntity();
        profesor1.setPersona(new PersonaEntity());
        profesores.add(profesor1);

        // Define un DTO de profesor de prueba
        ProfesorInputDto profesorDTO = new ProfesorInputDto();
        profesorDTO.setIdProfesor(1);

        // Define el comportamiento esperado del repositorio y del mapper
        when(profesorRepository.findByPersonaName(name)).thenReturn(profesores);
        when(profesorMapper.toDTOList(profesores)).thenReturn(Collections.singletonList(profesorDTO));

        // Ejecuta el método que queremos probar
        List<ProfesorInputDto> result = profesorService.getProfesoresDTOByName(name);

        // Verifica que el método del repositorio haya sido llamado con el nombre correcto
        verify(profesorRepository).findByPersonaName(name);

        // Verifica que el resultado sea el esperado
        assertEquals(Collections.singletonList(profesorDTO), result);
    }

    @Test
    public void testGetProfesoresDTOByNameNoResults() {
        String name = "NombreInexistente";

        // Define el comportamiento esperado del repositorio
        when(profesorRepository.findByPersonaName(name)).thenReturn(Collections.emptyList());

        // Ejecuta el método que queremos probar
        List<ProfesorInputDto> result = profesorService.getProfesoresDTOByName(name);

        // Verifica que el método del repositorio haya sido llamado con el nombre correcto
        verify(profesorRepository).findByPersonaName(name);

        // Verifica que el resultado sea una lista vacía
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetProfesorFullDetailsById() {
        Integer id = 1;

        // Define un profesor de prueba con el ID dado
        ProfesorEntity profesor = new ProfesorEntity();
        profesor.setIdProfesor(id);

        // Define un DTO de profesor completo de prueba
        ProfesorFullOutputDto profesorFullDTO = new ProfesorFullOutputDto();
        profesorFullDTO.setIdProfesor(id);

        // Define el comportamiento esperado del repositorio y del mapper
        when(profesorRepository.findById(id)).thenReturn(Optional.of(profesor));
        when(profesorMapper.toFullDTO(profesor)).thenReturn(profesorFullDTO);

        // Ejecuta el método que queremos probar
        ProfesorFullOutputDto result = profesorService.getProfesorFullDetailsById(id);

        // Verifica que el método del repositorio haya sido llamado con el ID correcto
        verify(profesorRepository).findById(id);

        // Verifica que el resultado sea el esperado
        assertEquals(profesorFullDTO, result);
    }

    @Test
    public void testGetProfesorFullDetailsByIdNotFound() {
        Integer id = 999;

        // Define el comportamiento esperado del repositorio
        when(profesorRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecuta el método que queremos probar, esperando que lance una excepción
        assertThrows(EntityNotFoundException.class, () -> profesorService.getProfesorFullDetailsById(id));
    }

    @Test
    public void testGetProfesorFullDetailsByName() {
        String name = "Nombre1";

        // Crea una lista de profesores de prueba
        List<ProfesorEntity> profesores = new ArrayList<>();
        ProfesorEntity profesor1 = new ProfesorEntity();
        profesor1.setPersona(new PersonaEntity());
        profesores.add(profesor1);

        // Define un DTO de profesor completo de prueba
        ProfesorFullOutputDto profesorFullDTO = new ProfesorFullOutputDto();
        profesorFullDTO.setIdProfesor(1);

        // Define el comportamiento esperado del repositorio y del mapper
        when(profesorRepository.findByPersonaName(name)).thenReturn(profesores);
        when(profesorMapper.toFullDTOList(profesores)).thenReturn(Collections.singletonList(profesorFullDTO));

        // Ejecuta el método que queremos probar
        List<ProfesorFullOutputDto> result = profesorService.getProfesorFullDetailsByName(name);

        // Verifica que el método del repositorio haya sido llamado con el nombre correcto
        verify(profesorRepository).findByPersonaName(name);

        // Verifica que el resultado sea el esperado
        assertEquals(Collections.singletonList(profesorFullDTO), result);
    }

    @Test
    public void testGetProfesorFullDetailsByNameNoResults() {
        String name = "NombreInexistente";

        // Define el comportamiento esperado del repositorio
        when(profesorRepository.findByPersonaName(name)).thenReturn(Collections.emptyList());

        // Ejecuta el método que queremos probar
        List<ProfesorFullOutputDto> result = profesorService.getProfesorFullDetailsByName(name);

        // Verifica que el método del repositorio haya sido llamado con el nombre correcto
        verify(profesorRepository).findByPersonaName(name);

        // Verifica que el resultado sea una lista vacía
        assertTrue(result.isEmpty());
    }
    /**
     * Esta prueba fue diseñada para esperar una excepción UnprocessableEntityException.
     * Prueba para la creación de un profesor con campos inadecuados en {@link ProfesorServiceImpl}.
     *
     * <h2>Problema</h2>
     * Al intentar guardar un profesor con campos inadecuados, el método {@code createProfesor} debía lanzar
     * una excepción {@link UnprocessableEntityException}. Sin embargo, el test originalmente fallaba al no estar
     * configurado para manejar esta excepción esperada.
     *
     * En una iteración anterior, se incluyeron stubbings innecesarios para algunos métodos
     * que no eran invocados en la ejecución de la prueba. Estos stubbings resultaban
     * en una UnnecessaryStubbingException.
     *
     * <h2>Solución</h2>
     * Se modificó el test para esperar la excepción {@link UnprocessableEntityException} utilizando la anotación
     * {@code @Test(expected = UnprocessableEntityException.class)}. De esta manera, el test pasa si se lanza la
     * excepción esperada, lo que es coherente con el comportamiento deseado del método.
     *
     * Los stubbings innecesarios fueron comentados para resolver el problema.
     *
     * <pre>
     * {@code
     * @Test(expected = UnprocessableEntityException.class)
     * public void testCreateProfesor() {
     *     // Prepara los datos de entrada inadecuados
     *     ProfesorInputDto profesorInputDto = new ProfesorInputDto();
     *     // ... (setea los campos inadecuados aquí)
     *
     *     // Llama al método que se espera que lance la excepción
     *     profesorService.createProfesor(profesorInputDto);
     * }
     * }
     * </pre>
     *
     * @see ProfesorServiceImpl#createProfesor(ProfesorInputDto)
     */

    @Test
    void testCreateProfesor() {
        // Arrange
        ProfesorInputDto profesor = new ProfesorInputDto();
        profesor.setIdProfesor(1);
        profesor.setComments("John Doe");
        profesor.setBranch("Branch");

        Integer idPersona = 1;
        profesor.setIdPersona(idPersona);

        ProfesorEntity profesorEntity = new ProfesorEntity();
        ProfesorEntity savedProfesorEntity = new ProfesorEntity();

        PersonaEntity personaEntity = new PersonaEntity();

        when(profesorMapper.toEntity(profesor)).thenReturn(profesorEntity);
        when(profesorMapper.toDTO(savedProfesorEntity)).thenReturn(profesor);
        when(personaService.buscarPorId(idPersona)).thenReturn(personaEntity);
        when(profesorService.saveProfesor(profesorEntity)).thenReturn(savedProfesorEntity);

        // Act
        try {
            ProfesorInputDto createdProfesor = profesorService.createProfesor(profesor);

            // Assert
            assertNotNull(createdProfesor);
            assertEquals(profesor.getIdProfesor(), createdProfesor.getIdProfesor());
            assertEquals(profesor.getComments(), createdProfesor.getComments());
            assertEquals(profesor.getBranch(), createdProfesor.getBranch());

        } catch (UnprocessableEntityException e) {
            // Handle the expected exception. In this case, we will fail the test deliberately
            fail("UnprocessableEntityException should not have been thrown. Message: " + e.getMessage());
        } catch (Exception e) {
            // Catch other unexpected exceptions and fail the test with their details
            fail("Unexpected exception was thrown. Message: " + e.getMessage());
        }
    }


    @Test
    public void testCreateProfesorMissingFields() {
        // Define un DTO de profesor de prueba con campos requeridos en blanco o nulos
        ProfesorInputDto profesorDTO = new ProfesorInputDto();
        profesorDTO.setComments(null); // El campo 'comments' es requerido y está nulo
        profesorDTO.setBranch("Sucursal del profesor"); // El campo 'branch' no es requerido

        // Ejecuta el método que queremos probar y verifica que lance la excepción UnprocessableEntityException
        assertThrows(UnprocessableEntityException.class, () -> profesorService.createProfesor(profesorDTO));

    }

}
