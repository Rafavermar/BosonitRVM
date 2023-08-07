package com.block13testingcrud.block13testingcrud.mappersTest;

import com.block13testingcrud.block13testingcrud.dto.input.PersonaInputDto;
import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.mapper.PersonaMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class PersonaMapperTest {

    private PersonaMapper personaMapper;

    @Before
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
        Assert.assertNotNull(result);

        // Verifica que los atributos del DTO se hayan mapeado correctamente a la entidad
        Assert.assertEquals(personaInputDto.getUsuario(), result.getUsuario());
        Assert.assertEquals(personaInputDto.getPassword(), result.getPassword());
        Assert.assertEquals(personaInputDto.getName(), result.getName());
        Assert.assertEquals(personaInputDto.getSurname(), result.getSurname());
        Assert.assertEquals(personaInputDto.getCompanyEmail(), result.getCompanyEmail());
        Assert.assertEquals(personaInputDto.getPersonalEmail(), result.getPersonalEmail());
        Assert.assertEquals(personaInputDto.getCity(), result.getCity());
        Assert.assertEquals(personaInputDto.isActive(), result.isActive());
        Assert.assertEquals(personaInputDto.getCreated_date(), result.getCreatedDate());
        Assert.assertEquals(personaInputDto.getImagenUrl(), result.getImageUrl());
        Assert.assertEquals(personaInputDto.getTermination_date(), result.getTerminationDate());
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
        Assert.assertNotNull(result);

        // Verifica que los atributos de la entidad se hayan mapeado correctamente al DTO
        Assert.assertEquals(personaEntity.getIdPersona(), result.getId());
        Assert.assertEquals(personaEntity.getUsuario(), result.getUsuario());
        Assert.assertEquals(personaEntity.getPassword(), result.getPassword());
        Assert.assertEquals(personaEntity.getName(), result.getName());
        Assert.assertEquals(personaEntity.getSurname(), result.getSurname());
        Assert.assertEquals(personaEntity.getCompanyEmail(), result.getCompanyEmail());
        Assert.assertEquals(personaEntity.getPersonalEmail(), result.getPersonalEmail());
        Assert.assertEquals(personaEntity.getCity(), result.getCity());
        Assert.assertEquals(personaEntity.isActive(), result.isActive());
        Assert.assertEquals(personaEntity.getCreatedDate(), result.getCreated_date());
        Assert.assertEquals(personaEntity.getImageUrl(), result.getImagenUrl());
        Assert.assertEquals(personaEntity.getTerminationDate(), result.getTermination_date());
    }
}
