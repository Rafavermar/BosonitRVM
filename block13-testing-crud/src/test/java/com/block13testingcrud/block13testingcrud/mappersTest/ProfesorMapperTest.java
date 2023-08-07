package com.block13testingcrud.block13testingcrud.mappersTest;

import com.block13testingcrud.block13testingcrud.dto.input.ProfesorInputDto;
import com.block13testingcrud.block13testingcrud.dto.output.ProfesorFullOutputDto;
import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.entities.ProfesorEntity;
import com.block13testingcrud.block13testingcrud.mapper.ProfesorMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class ProfesorMapperTest {

    private ProfesorMapper profesorMapper;

    @Before
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
        Assert.assertNotNull(result);

        // Verifica que los atributos del DTO se hayan mapeado correctamente a la entidad
        Assert.assertEquals(profesorInputDto.getIdProfesor(), result.getIdProfesor());
        Assert.assertEquals(profesorInputDto.getIdPersona(), result.getPersona().getIdPersona());
        Assert.assertEquals(profesorInputDto.getComments(), result.getComments());
        Assert.assertEquals(profesorInputDto.getBranch(), result.getBranch());
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
        Assert.assertNotNull(result);

        // Verifica que los atributos de la entidad se hayan mapeado correctamente al DTO
        Assert.assertEquals(profesorEntity.getIdProfesor(), result.getIdProfesor());
        Assert.assertEquals(profesorEntity.getPersona().getIdPersona(), result.getIdPersona());
        Assert.assertEquals(profesorEntity.getComments(), result.getComments());
        Assert.assertEquals(profesorEntity.getBranch(), result.getBranch());
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
        Assert.assertNotNull(result);

        // Verifica que los atributos de la entidad y de la entidad PersonaEntity asociada se hayan mapeado correctamente al DTO completo
        Assert.assertEquals(profesorEntity.getIdProfesor(), result.getIdProfesor());
        Assert.assertEquals(profesorEntity.getPersona().getIdPersona(), result.getIdPersona());
        Assert.assertEquals(profesorEntity.getPersona().getUsuario(), result.getUsuario());
        Assert.assertEquals(profesorEntity.getPersona().getName(), result.getName());
        Assert.assertEquals(profesorEntity.getPersona().getSurname(), result.getSurname());
        Assert.assertEquals(profesorEntity.getPersona().getCompanyEmail(), result.getCompanyEmail());
        Assert.assertEquals(profesorEntity.getPersona().getPersonalEmail(), result.getPersonalEmail());
        Assert.assertEquals(profesorEntity.getPersona().getCity(), result.getCity());
        Assert.assertEquals(profesorEntity.getPersona().isActive(), result.isActive());
        Assert.assertEquals(profesorEntity.getPersona().getCreatedDate(), result.getCreatedDate());
        Assert.assertEquals(profesorEntity.getPersona().getImageUrl(), result.getImageUrl());
        Assert.assertEquals(profesorEntity.getPersona().getTerminationDate(), result.getTerminationDate());
        Assert.assertEquals(profesorEntity.getComments(), result.getComments());
        Assert.assertEquals(profesorEntity.getBranch(), result.getBranch());
    }
}
