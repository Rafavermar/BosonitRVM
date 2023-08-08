package com.block13testingcrud.block13testingcrud.mappersTest;

import com.block13testingcrud.block13testingcrud.dto.input.PersonaInputDto;
import com.block13testingcrud.block13testingcrud.dto.input.ProfesorInputDto;
import com.block13testingcrud.block13testingcrud.dto.input.StudentInputDto;
import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.entities.ProfesorEntity;
import com.block13testingcrud.block13testingcrud.entities.StudentEntity;
import com.block13testingcrud.block13testingcrud.mapper.StudentMapper;
import com.block13testingcrud.block13testingcrud.repository.ProfesorRepository;
import com.block13testingcrud.block13testingcrud.services.PersonaService;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Clase de pruebas para el mapeador StudentMapper.
 * Se utilizan pruebas unitarias con Mockito para verificar el correcto mapeo entre
 * entidades y DTOs, y para asegurar que los stubbings de dependencias se ejecuten sin problemas.
 * Se han resuelto las advertencias de UnnecessaryStubbingException marcando ciertos stubbings como "lenient"
 * mediante el uso de Mockito.lenient(). Esto evita lanzar excepciones cuando un stubbing no se utiliza
 * en todas las pruebas, pero sigue siendo necesario para otras.
 */

@ExtendWith(MockitoExtension.class)
public class StudentMapperTest {

    private StudentMapper studentMapper;

    @Mock
    private PersonaService personaService;

    @Mock
    private ProfesorRepository profesorRepository;

    @BeforeEach
    public void setUp() {
        // Inicializa el mapper antes de cada prueba
        studentMapper = new StudentMapper();
        studentMapper.personaService = personaService;
        studentMapper.profesorRepository = profesorRepository;
    }

    @Test
    public void testToEntity() {
        // Crea un DTO de prueba
        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setIdStudent(1);
        studentInputDto.setIdPersona(1001); // ID de PersonaEntity asociada
        studentInputDto.setNumHoursWeek(20);
        studentInputDto.setComments("Comentarios del estudiante");
        studentInputDto.setIdProfesor(10); // ID de ProfesorEntity asociado
        studentInputDto.setBranch("Ciencias");

        // Crea las entidades mock para la PersonaEntity y ProfesorEntity asociadas
        PersonaEntity personaEntityMock = Mockito.mock(PersonaEntity.class);
        ProfesorEntity profesorEntityMock = Mockito.mock(ProfesorEntity.class);

        // Configura el comportamiento de los mocks
        Mockito.when(personaService.buscarPorId(studentInputDto.getIdPersona())).thenReturn(personaEntityMock);
        Mockito.when(profesorRepository.findById(studentInputDto.getIdProfesor())).thenReturn(Optional.of(profesorEntityMock));

        // Establece las dependencias en el mapper
        studentMapper.personaService = personaService;
        studentMapper.profesorRepository = profesorRepository;

        // Ejecuta el método que queremos probar
        StudentEntity result = studentMapper.toEntity(studentInputDto);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos del DTO se hayan mapeado correctamente a la entidad
        assertEquals(studentInputDto.getIdStudent(), result.getIdStudent());
        assertEquals(personaEntityMock, result.getPersona());
        assertEquals(studentInputDto.getNumHoursWeek(), result.getNumHoursWeek());
        assertEquals(studentInputDto.getComments(), result.getComments());
        assertEquals(profesorEntityMock, result.getProfesor());
        assertEquals(studentInputDto.getBranch(), result.getBranch());
    }

    @Test
    public void testToDTO() {
        // Crea una entidad de prueba
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setIdStudent(1);
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1001); // ID de PersonaEntity asociada
        studentEntity.setPersona(personaEntity);
        studentEntity.setNumHoursWeek(20);
        studentEntity.setComments("Comentarios del estudiante");
        ProfesorEntity profesorEntity = new ProfesorEntity();
        profesorEntity.setIdProfesor(10); // ID de ProfesorEntity asociado
        studentEntity.setProfesor(profesorEntity);
        studentEntity.setBranch("Ciencias");

        // Establece las dependencias en el mapper
        studentMapper.personaService = personaService;
        studentMapper.profesorRepository = profesorRepository;

        // Ejecuta el método que queremos probar
        StudentInputDto result = studentMapper.toDTO(studentEntity);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos de la entidad se hayan mapeado correctamente al DTO
        assertEquals(studentEntity.getIdStudent(), result.getIdStudent());
        assertEquals(studentEntity.getPersona().getIdPersona(), result.getIdPersona());
        assertEquals(studentEntity.getNumHoursWeek(), result.getNumHoursWeek());
        assertEquals(studentEntity.getComments(), result.getComments());
        assertEquals(studentEntity.getProfesor().getIdProfesor(), result.getIdProfesor());
        assertEquals(studentEntity.getBranch(), result.getBranch());
    }

    @Test
    public void testConvertToDTO() {
        // Crea una entidad de prueba
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1001);
        personaEntity.setUsuario("usuario123");
        personaEntity.setName("Nombre");
        personaEntity.setSurname("Apellido");
        personaEntity.setCompanyEmail("usuario@company.com");
        personaEntity.setPersonalEmail("usuario@personal.com");
        personaEntity.setCity("Ciudad");
        personaEntity.setActive(true);
        personaEntity.setCreatedDate(new Date());
        personaEntity.setImageUrl("imagen_url");
        personaEntity.setTerminationDate(null);

        // Ejecuta el método que queremos probar
        PersonaInputDto result = studentMapper.convertToDTO(personaEntity);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos de la entidad se hayan mapeado correctamente al DTO
        assertEquals(personaEntity.getIdPersona(), result.getId());
        assertEquals(personaEntity.getUsuario(), result.getUsuario());
        assertEquals(personaEntity.getName(), result.getName());
        assertEquals(personaEntity.getSurname(), result.getSurname());
        assertEquals(personaEntity.getCompanyEmail(), result.getCompanyEmail());
        assertEquals(personaEntity.getPersonalEmail(), result.getPersonalEmail());
        assertEquals(personaEntity.getCity(), result.getCity());
        assertEquals(personaEntity.isActive(), result.isActive());
        assertEquals(personaEntity.getCreatedDate(), result.getCreated_date());
        assertEquals(personaEntity.getImageUrl(), result.getImagenUrl());
        assertEquals(personaEntity.getTerminationDate(), result.getTermination_date());
    }

    @Test
    public void testToDTOList() {
        // Crea una lista de entidades de prueba
        List<PersonaEntity> personaEntities = new ArrayList<>();
        PersonaEntity personaEntity1 = new PersonaEntity();
        personaEntity1.setIdPersona(1001);
        personaEntities.add(personaEntity1);

        PersonaEntity personaEntity2 = new PersonaEntity();
        personaEntity2.setIdPersona(1002);
        personaEntities.add(personaEntity2);

        // Mockear el comportamiento del PersonaService usando lenient()
        Mockito.lenient().when(personaService.buscarPorId(Mockito.anyInt())).thenReturn(personaEntity1);

        // Ejecuta el método que queremos probar
        List<PersonaInputDto> result = studentMapper.toDTOList(personaEntities);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que la lista resultante tenga la misma cantidad de elementos que la lista de entidades
        assertEquals(personaEntities.size(), result.size());

        // Verifica que los atributos de las entidades se hayan mapeado correctamente a los DTOs
        assertEquals(personaEntities.get(0).getIdPersona(), result.get(0).getId());
        assertEquals(personaEntities.get(1).getIdPersona(), result.get(1).getId());
    }

    @Test
    public void testConvertToDTOForProfesor() {
        // Crea una entidad Profesor de prueba
        ProfesorEntity profesorEntity = new ProfesorEntity();
        profesorEntity.setIdProfesor(10);

        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1001);
        profesorEntity.setPersona(personaEntity);

        profesorEntity.setComments("Comentarios del profesor");
        profesorEntity.setBranch("Ciencias");

        // Ejecuta el método que queremos probar
        ProfesorInputDto result = studentMapper.convertToDTO(profesorEntity);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos de la entidad se hayan mapeado correctamente al DTO
        assertEquals(profesorEntity.getIdProfesor(), result.getIdProfesor());
        assertEquals(profesorEntity.getPersona().getIdPersona(), result.getIdPersona());
        assertEquals(profesorEntity.getComments(), result.getComments());
        assertEquals(profesorEntity.getBranch(), result.getBranch());
    }

}
