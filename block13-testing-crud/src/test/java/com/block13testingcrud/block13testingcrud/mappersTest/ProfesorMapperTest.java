package com.block13testingcrud.block13testingcrud.mappersTest;

import com.block13testingcrud.block13testingcrud.dto.input.ProfesorInputDto;
import com.block13testingcrud.block13testingcrud.dto.output.ProfesorFullOutputDto;
import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.entities.ProfesorEntity;
import com.block13testingcrud.block13testingcrud.mapper.ProfesorMapper;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProfesorMapperTest {

    private ProfesorMapper profesorMapper;

    @BeforeEach
    public void setUp() {
        // Inicializa el mapper antes de cada prueba
        profesorMapper = new ProfesorMapper();
    }

    @Test
    public void testToEntity() {
        // Crea un DTO de prueba
        ProfesorInputDto profesorInputDto = new ProfesorInputDto();
        profesorInputDto.setIdProfesor(1);
        profesorInputDto.setIdPersona(1001); // ID de PersonaEntity asociada
        profesorInputDto.setComments("Comentarios del profesor");
        profesorInputDto.setBranch("Ciencias");

        // Ejecuta el método que queremos probar
        ProfesorEntity result = profesorMapper.toEntity(profesorInputDto);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos del DTO se hayan mapeado correctamente a la entidad
        assertEquals(profesorInputDto.getIdProfesor(), result.getIdProfesor());
        assertEquals(profesorInputDto.getIdPersona(), result.getPersona().getIdPersona());
        assertEquals(profesorInputDto.getComments(), result.getComments());
        assertEquals(profesorInputDto.getBranch(), result.getBranch());
    }

    @Test
    public void testToDTO() {
        // Crea una entidad de prueba
        ProfesorEntity profesorEntity = new ProfesorEntity();
        profesorEntity.setIdProfesor(1);
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1001); // ID de PersonaEntity asociada
        profesorEntity.setPersona(personaEntity);
        profesorEntity.setComments("Comentarios del profesor");
        profesorEntity.setBranch("Ciencias");

        // Ejecuta el método que queremos probar
        ProfesorInputDto result = profesorMapper.toDTO(profesorEntity);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos de la entidad se hayan mapeado correctamente al DTO
        assertEquals(profesorEntity.getIdProfesor(), result.getIdProfesor());
        assertEquals(profesorEntity.getPersona().getIdPersona(), result.getIdPersona());
        assertEquals(profesorEntity.getComments(), result.getComments());
        assertEquals(profesorEntity.getBranch(), result.getBranch());
    }

    @Test
    public void testToFullDTO() {
        // Crea una entidad de prueba
        ProfesorEntity profesorEntity = new ProfesorEntity();
        profesorEntity.setIdProfesor(1);
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1001); // ID de PersonaEntity asociada
        personaEntity.setUsuario("user123");
        personaEntity.setName("John");
        personaEntity.setSurname("Doe");
        personaEntity.setCompanyEmail("john.doe@company.com");
        personaEntity.setPersonalEmail("john.doe@gmail.com");
        personaEntity.setCity("New York");
        personaEntity.setActive(true);
        Date createdDate = Mockito.mock(Date.class);
        personaEntity.setCreatedDate(createdDate);
        personaEntity.setImageUrl("http://example.com/image.jpg");
        Date terminationDate = Mockito.mock(Date.class);
        personaEntity.setTerminationDate(terminationDate);
        profesorEntity.setPersona(personaEntity);
        profesorEntity.setComments("Comentarios del profesor");
        profesorEntity.setBranch("Ciencias");

        // Ejecuta el método que queremos probar
        ProfesorFullOutputDto result = profesorMapper.toFullDTO(profesorEntity);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos de la entidad y de la entidad PersonaEntity asociada se hayan mapeado correctamente al DTO completo
        assertEquals(profesorEntity.getIdProfesor(), result.getIdProfesor());
        assertEquals(profesorEntity.getPersona().getIdPersona(), result.getIdPersona());
        assertEquals(profesorEntity.getPersona().getUsuario(), result.getUsuario());
        assertEquals(profesorEntity.getPersona().getName(), result.getName());
        assertEquals(profesorEntity.getPersona().getSurname(), result.getSurname());
        assertEquals(profesorEntity.getPersona().getCompanyEmail(), result.getCompanyEmail());
        assertEquals(profesorEntity.getPersona().getPersonalEmail(), result.getPersonalEmail());
        assertEquals(profesorEntity.getPersona().getCity(), result.getCity());
        assertEquals(profesorEntity.getPersona().isActive(), result.isActive());
        assertEquals(profesorEntity.getPersona().getCreatedDate(), result.getCreatedDate());
        assertEquals(profesorEntity.getPersona().getImageUrl(), result.getImageUrl());
        assertEquals(profesorEntity.getPersona().getTerminationDate(), result.getTerminationDate());
        assertEquals(profesorEntity.getComments(), result.getComments());
        assertEquals(profesorEntity.getBranch(), result.getBranch());
    }
}
