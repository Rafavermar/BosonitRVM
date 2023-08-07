package com.block13testingcrud.block13testingcrud.mappersTest;

import com.block13testingcrud.block13testingcrud.dto.input.PersonaInputDto;
import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.mapper.PersonaMapper;

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
public class PersonaMapperTest {

    private PersonaMapper personaMapper;

    @BeforeEach
    public void setUp() {
        // Inicializa el mapper antes de cada prueba
        personaMapper = new PersonaMapper();
    }

    @Test
    public void testToEntity() {
        // Crea un DTO de prueba
        PersonaInputDto personaInputDto = new PersonaInputDto();
        personaInputDto.setUsuario("user123");
        personaInputDto.setPassword("pass123");
        personaInputDto.setName("John");
        personaInputDto.setSurname("Doe");
        personaInputDto.setCompanyEmail("john.doe@company.com");
        personaInputDto.setPersonalEmail("john.doe@gmail.com");
        personaInputDto.setCity("New York");
        personaInputDto.setActive(true);
        Date createdDate = Mockito.mock(Date.class);
        personaInputDto.setCreated_date(createdDate);
        personaInputDto.setImagenUrl("http://example.com/image.jpg");
        Date terminationDate = Mockito.mock(Date.class);
        personaInputDto.setTermination_date(terminationDate);

        // Ejecuta el método que queremos probar
        PersonaEntity result = personaMapper.toEntity(personaInputDto);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos del DTO se hayan mapeado correctamente a la entidad
        assertEquals(personaInputDto.getUsuario(), result.getUsuario());
        assertEquals(personaInputDto.getPassword(), result.getPassword());
        assertEquals(personaInputDto.getName(), result.getName());
        assertEquals(personaInputDto.getSurname(), result.getSurname());
        assertEquals(personaInputDto.getCompanyEmail(), result.getCompanyEmail());
        assertEquals(personaInputDto.getPersonalEmail(), result.getPersonalEmail());
        assertEquals(personaInputDto.getCity(), result.getCity());
        assertEquals(personaInputDto.isActive(), result.isActive());
        assertEquals(personaInputDto.getCreated_date(), result.getCreatedDate());
        assertEquals(personaInputDto.getImagenUrl(), result.getImageUrl());
        assertEquals(personaInputDto.getTermination_date(), result.getTerminationDate());
    }

    @Test
    public void testToDTO() {
        // Crea una entidad de prueba
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1);
        personaEntity.setUsuario("user123");
        personaEntity.setPassword("pass123");
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

        // Ejecuta el método que queremos probar
        PersonaInputDto result = personaMapper.toDTO(personaEntity);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos de la entidad se hayan mapeado correctamente al DTO
        assertEquals(personaEntity.getIdPersona(), result.getId());
        assertEquals(personaEntity.getUsuario(), result.getUsuario());
        assertEquals(personaEntity.getPassword(), result.getPassword());
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
}
