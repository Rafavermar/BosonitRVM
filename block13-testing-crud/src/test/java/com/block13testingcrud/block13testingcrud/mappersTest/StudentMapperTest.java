package com.block13testingcrud.block13testingcrud.mappersTest;

import com.block13testingcrud.block13testingcrud.dto.input.PersonaInputDto;
import com.block13testingcrud.block13testingcrud.dto.input.StudentInputDto;
import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.entities.ProfesorEntity;
import com.block13testingcrud.block13testingcrud.entities.StudentEntity;
import com.block13testingcrud.block13testingcrud.mapper.StudentMapper;
import com.block13testingcrud.block13testingcrud.repository.ProfesorRepository;
import com.block13testingcrud.block13testingcrud.services.PersonaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Clase de pruebas para el mapeador StudentMapper.
 * Se utilizan pruebas unitarias con Mockito para verificar el correcto mapeo entre
 * entidades y DTOs, y para asegurar que los stubbings de dependencias se ejecuten sin problemas.
 * Se han resuelto las advertencias de UnnecessaryStubbingException marcando ciertos stubbings como "lenient"
 * mediante el uso de Mockito.lenient(). Esto evita lanzar excepciones cuando un stubbing no se utiliza
 * en todas las pruebas, pero sigue siendo necesario para otras.
 */

@RunWith(MockitoJUnitRunner.class)
public class StudentMapperTest {

    private StudentMapper studentMapper;

    @Mock
    private PersonaService personaService;

    @Mock
    private ProfesorRepository profesorRepository;

    @Before
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
        Assert.assertNotNull(result);

        // Verifica que los atributos del DTO se hayan mapeado correctamente a la entidad
        Assert.assertEquals(studentInputDto.getIdStudent(), result.getIdStudent());
        Assert.assertEquals(personaEntityMock, result.getPersona());
        Assert.assertEquals(studentInputDto.getNumHoursWeek(), result.getNumHoursWeek());
        Assert.assertEquals(studentInputDto.getComments(), result.getComments());
        Assert.assertEquals(profesorEntityMock, result.getProfesor());
        Assert.assertEquals(studentInputDto.getBranch(), result.getBranch());
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
        Assert.assertNotNull(result);

        // Verifica que los atributos de la entidad se hayan mapeado correctamente al DTO
        Assert.assertEquals(studentEntity.getIdStudent(), result.getIdStudent());
        Assert.assertEquals(studentEntity.getPersona().getIdPersona(), result.getIdPersona());
        Assert.assertEquals(studentEntity.getNumHoursWeek(), result.getNumHoursWeek());
        Assert.assertEquals(studentEntity.getComments(), result.getComments());
        Assert.assertEquals(studentEntity.getProfesor().getIdProfesor(), result.getIdProfesor());
        Assert.assertEquals(studentEntity.getBranch(), result.getBranch());
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
        Assert.assertNotNull(result);

        // Verifica que los atributos de la entidad se hayan mapeado correctamente al DTO
        Assert.assertEquals(personaEntity.getIdPersona(), result.getId());
        Assert.assertEquals(personaEntity.getUsuario(), result.getUsuario());
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
        Assert.assertNotNull(result);

        // Verifica que la lista resultante tenga la misma cantidad de elementos que la lista de entidades
        Assert.assertEquals(personaEntities.size(), result.size());

        // Verifica que los atributos de las entidades se hayan mapeado correctamente a los DTOs
        Assert.assertEquals(personaEntities.get(0).getIdPersona(), result.get(0).getId());
        Assert.assertEquals(personaEntities.get(1).getIdPersona(), result.get(1).getId());
    }
}
