package com.block13testingcrud.block13testingcrud.servicesTest;

import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.exception.EntityByNameNotFoundException;
import com.block13testingcrud.block13testingcrud.exception.EntityNotFoundException;
import com.block13testingcrud.block13testingcrud.exception.UnprocessableEntityException;
import com.block13testingcrud.block13testingcrud.repository.PersonaRepository;
import com.block13testingcrud.block13testingcrud.repository.ProfesorEstudianteRepository;
import com.block13testingcrud.block13testingcrud.repository.ProfesorRepository;
import com.block13testingcrud.block13testingcrud.repository.StudentRepository;
import com.block13testingcrud.block13testingcrud.services.PersonaServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PersonaServiceImplTest {

    @InjectMocks
    private PersonaServiceImpl personaService;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ProfesorEstudianteRepository profesorEstudianteRepository;

    @Test
    public void testAgregarPersona() {
        // Define una persona de prueba
        PersonaEntity persona = new PersonaEntity();
        persona.setUsuario("usuario1");
        persona.setName("Nombre1");
        persona.setCity("Ciudad1");

        // Define el comportamiento esperado del repositorio
        Mockito.when(personaRepository.save(persona)).thenReturn(persona);

        // Ejecuta el método que queremos probar
        PersonaEntity result = personaService.agregarPersona(persona);

        // Verifica que el método del repositorio haya sido llamado con la persona correcta
        Mockito.verify(personaRepository).save(persona);

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(persona, result);
    }

    @Test(expected = UnprocessableEntityException.class)
    public void testAgregarPersonaMissingFields() {
        // Define una persona de prueba sin campos requeridos
        PersonaEntity persona = new PersonaEntity();

        // Ejecuta el método que queremos probar, esperando que lance una excepción
        personaService.agregarPersona(persona);
    }

    @Test
    public void testBuscarPorId() {
        Integer id = 1;

        // Define una persona de prueba con el ID dado
        PersonaEntity persona = new PersonaEntity();
        persona.setIdPersona(id);

        // Define el comportamiento esperado del repositorio
        Mockito.when(personaRepository.findById(id)).thenReturn(Optional.of(persona));

        // Ejecuta el método que queremos probar
        PersonaEntity result = personaService.buscarPorId(id);

        // Verifica que el método del repositorio haya sido llamado con el ID correcto
        Mockito.verify(personaRepository).findById(id);

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(persona, result);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testBuscarPorIdNotFound() {
        Integer id = 999;

        // Define el comportamiento esperado del repositorio
        Mockito.when(personaRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecuta el método que queremos probar, esperando que lance una excepción
        personaService.buscarPorId(id);
    }

    @Test
    public void testBuscarPorUsuario() {
        String usuario = "usuario1";

        // Define una persona de prueba con el usuario dado
        PersonaEntity persona = new PersonaEntity();
        persona.setIdPersona(1);
        persona.setUsuario(usuario);

        // Define el comportamiento esperado del repositorio
        Mockito.when(personaRepository.findByUsuario(usuario)).thenReturn(Optional.of(persona));

        // Ejecuta el método que queremos probar
        PersonaEntity result = personaService.buscarPorUsuario(usuario);

        // Verifica que el método del repositorio haya sido llamado con el usuario correcto
        Mockito.verify(personaRepository).findByUsuario(usuario);

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(persona, result);
    }

    @Test(expected = EntityByNameNotFoundException.class)
    public void testBuscarPorUsuarioNotFound() {
        String usuario = "usuario999";

        // Define el comportamiento esperado del repositorio
        Mockito.when(personaRepository.findByUsuario(usuario)).thenReturn(Optional.empty());

        // Ejecuta el método que queremos probar, esperando que lance una excepción
        personaService.buscarPorUsuario(usuario);
    }

    @Test
    public void testMostrarTodos() {
        // Crea una lista de personas de prueba
        List<PersonaEntity> personas = new ArrayList<>();
        personas.add(new PersonaEntity());
        personas.add(new PersonaEntity());

        // Define el comportamiento esperado del repositorio
        Mockito.when(personaRepository.findAll()).thenReturn(personas);

        // Ejecuta el método que queremos probar
        List<PersonaEntity> result = personaService.mostrarTodos();

        // Verifica que el método del repositorio haya sido llamado
        Mockito.verify(personaRepository).findAll();

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(personas, result);
    }

    @Test
    public void testBorrarPersona() {
        Integer id = 1;

        // Define una persona de prueba con el ID dado
        PersonaEntity persona = new PersonaEntity();
        persona.setIdPersona(id);

        // Define el comportamiento esperado del repositorio
        Mockito.when(personaRepository.findById(id)).thenReturn(Optional.of(persona));
        Mockito.when(profesorRepository.findByPersona(persona)).thenReturn(null);
        Mockito.when(studentRepository.findByPersona(persona)).thenReturn(null);

        // Ejecuta el método que queremos probar
        personaService.borrarPersona(id);

        // Verifica que el método del repositorio haya sido llamado con el ID correcto
        Mockito.verify(personaRepository).findById(id);

        // Verifica que el método del repositorio haya sido llamado para eliminar la persona
        Mockito.verify(personaRepository).delete(persona);
    }

    @Test
    public void testModificarPersona() {
        Integer id = 1;

        // Define una persona de prueba con el ID dado
        PersonaEntity personaActual = new PersonaEntity();
        personaActual.setIdPersona(id);
        personaActual.setUsuario("usuario1");
        personaActual.setName("Nombre1");
        personaActual.setCity("Ciudad1");

        // Define una persona de prueba con los campos a modificar
        PersonaEntity personaModificar = new PersonaEntity();
        personaModificar.setUsuario("usuario1_modificado");
        personaModificar.setName("Nombre1_modificado");
        personaModificar.setCity("Ciudad1_modificado");

        // Define el comportamiento esperado del repositorio
        Mockito.when(personaRepository.findById(id)).thenReturn(Optional.of(personaActual));
        Mockito.when(personaRepository.save(personaActual)).thenReturn(personaActual);

        // Ejecuta el método que queremos probar
        PersonaEntity result = personaService.modificarPersona(id, personaModificar);

        // Verifica que el método del repositorio haya sido llamado con el ID correcto
        Mockito.verify(personaRepository).findById(id);

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(personaModificar.getUsuario(), result.getUsuario());
        Assert.assertEquals(personaModificar.getName(), result.getName());
        Assert.assertEquals(personaModificar.getCity(), result.getCity());
    }

    @Test(expected = UnprocessableEntityException.class)
    public void testModificarPersonaMissingFields() {
        Integer id = 1;

        // Define una persona de prueba con el ID dado
        PersonaEntity personaActual = new PersonaEntity();
        personaActual.setIdPersona(id);
        personaActual.setUsuario("usuario1");
        personaActual.setName("Nombre1");
        personaActual.setCity("Ciudad1");

        // Define una persona de prueba sin campos requeridos
        PersonaEntity personaModificar = new PersonaEntity();

        // Ejecuta el método que queremos probar, esperando que lance una excepción
        personaService.modificarPersona(id, personaModificar);
    }

}
