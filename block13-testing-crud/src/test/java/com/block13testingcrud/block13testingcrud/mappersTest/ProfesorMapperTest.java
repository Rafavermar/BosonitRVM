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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testToDTOList() {
        // Crea una lista de entidades de prueba
        ProfesorEntity profesorEntity1 = new ProfesorEntity();
        profesorEntity1.setIdProfesor(1);
        PersonaEntity personaEntity1 = new PersonaEntity();
        personaEntity1.setIdPersona(1001); // ID de PersonaEntity asociada
        profesorEntity1.setPersona(personaEntity1);
        profesorEntity1.setComments("Comentarios del profesor 1");
        profesorEntity1.setBranch("Ciencias");

        ProfesorEntity profesorEntity2 = new ProfesorEntity();
        profesorEntity2.setIdProfesor(2);
        PersonaEntity personaEntity2 = new PersonaEntity();
        personaEntity2.setIdPersona(1002); // ID de PersonaEntity asociada
        profesorEntity2.setPersona(personaEntity2);
        profesorEntity2.setComments("Comentarios del profesor 2");
        profesorEntity2.setBranch("Artes");

        List<ProfesorEntity> profesorEntities = Arrays.asList(profesorEntity1, profesorEntity2);

        // Ejecuta el método que queremos probar
        List<ProfesorInputDto> resultList = profesorMapper.toDTOList(profesorEntities);

        // Verifica que el resultado no sea nulo
        assertNotNull(resultList);

        // Verifica que la lista resultante tenga el mismo tamaño que la lista original
        assertEquals(profesorEntities.size(), resultList.size());

        // Verifica que los atributos de las entidades se hayan mapeado correctamente a los DTOs
        for (int i = 0; i < profesorEntities.size(); i++) {
            assertEquals(profesorEntities.get(i).getIdProfesor(), resultList.get(i).getIdProfesor());
            assertEquals(profesorEntities.get(i).getPersona().getIdPersona(), resultList.get(i).getIdPersona());
            assertEquals(profesorEntities.get(i).getComments(), resultList.get(i).getComments());
            assertEquals(profesorEntities.get(i).getBranch(), resultList.get(i).getBranch());
        }

    }

    @Test
    public void testToFullDTOList() {
        // Crea una lista de entidades de prueba
        ProfesorEntity profesorEntity1 = new ProfesorEntity();
        profesorEntity1.setIdProfesor(1);
        PersonaEntity personaEntity1 = new PersonaEntity();
        personaEntity1.setIdPersona(1001); // ID de PersonaEntity asociada
        personaEntity1.setUsuario("user123");
        personaEntity1.setName("John");
        personaEntity1.setSurname("Doe");
        personaEntity1.setCompanyEmail("john.doe@company.com");
        personaEntity1.setPersonalEmail("john.doe@gmail.com");
        personaEntity1.setCity("New York");
        personaEntity1.setActive(true);
        Date createdDate1 = Mockito.mock(Date.class);
        personaEntity1.setCreatedDate(createdDate1);
        personaEntity1.setImageUrl("http://example.com/image1.jpg");
        Date terminationDate1 = Mockito.mock(Date.class);
        personaEntity1.setTerminationDate(terminationDate1);
        profesorEntity1.setPersona(personaEntity1);
        profesorEntity1.setComments("Comentarios del profesor 1");
        profesorEntity1.setBranch("Ciencias");

        ProfesorEntity profesorEntity2 = new ProfesorEntity();
        profesorEntity2.setIdProfesor(2);
        PersonaEntity personaEntity2 = new PersonaEntity();
        personaEntity2.setIdPersona(1002); // ID de PersonaEntity asociada
        personaEntity2.setUsuario("user456");
        personaEntity2.setName("Jane");
        personaEntity2.setSurname("Smith");
        personaEntity2.setCompanyEmail("jane.smith@company.com");
        personaEntity2.setPersonalEmail("jane.smith@gmail.com");
        personaEntity2.setCity("Los Angeles");
        personaEntity2.setActive(false);
        Date createdDate2 = Mockito.mock(Date.class);
        personaEntity2.setCreatedDate(createdDate2);
        personaEntity2.setImageUrl("http://example.com/image2.jpg");
        Date terminationDate2 = Mockito.mock(Date.class);
        personaEntity2.setTerminationDate(terminationDate2);
        profesorEntity2.setPersona(personaEntity2);
        profesorEntity2.setComments("Comentarios del profesor 2");
        profesorEntity2.setBranch("Artes");

        List<ProfesorEntity> profesorEntities = Arrays.asList(profesorEntity1, profesorEntity2);

        // Ejecuta el método que queremos probar
        List<ProfesorFullOutputDto> resultList = profesorMapper.toFullDTOList(profesorEntities);

        // Verifica que el resultado no sea nulo
        assertNotNull(resultList);

        // Verifica que la lista resultante tenga el mismo tamaño que la lista original
        assertEquals(profesorEntities.size(), resultList.size());

        // Verifica que los atributos de las entidades y de las entidades PersonaEntity asociadas se hayan mapeado correctamente a los DTOs completos
        for (int i = 0; i < profesorEntities.size(); i++) {
            ProfesorEntity entity = profesorEntities.get(i);
            ProfesorFullOutputDto dto = resultList.get(i);

            assertEquals(entity.getIdProfesor(), dto.getIdProfesor());
            assertEquals(entity.getPersona().getIdPersona(), dto.getIdPersona());
            assertEquals(entity.getPersona().getUsuario(), dto.getUsuario());
            assertEquals(entity.getPersona().getName(), dto.getName());
            assertEquals(entity.getPersona().getSurname(), dto.getSurname());
            assertEquals(entity.getPersona().getCompanyEmail(), dto.getCompanyEmail());
            assertEquals(entity.getPersona().getPersonalEmail(), dto.getPersonalEmail());
            assertEquals(entity.getPersona().getCity(), dto.getCity());
            assertEquals(entity.getPersona().isActive(), dto.isActive());
            assertEquals(entity.getPersona().getCreatedDate(), dto.getCreatedDate());
            assertEquals(entity.getPersona().getImageUrl(), dto.getImageUrl());
            assertEquals(entity.getPersona().getTerminationDate(), dto.getTerminationDate());
            assertEquals(entity.getComments(), dto.getComments());
            assertEquals(entity.getBranch(), dto.getBranch());
        }
    }

}
