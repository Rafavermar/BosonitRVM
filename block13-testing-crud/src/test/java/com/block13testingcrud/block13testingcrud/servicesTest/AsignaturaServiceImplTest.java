package com.block13testingcrud.block13testingcrud.servicesTest;

import com.block13testingcrud.block13testingcrud.dto.input.AsignaturaInputDTO;
import com.block13testingcrud.block13testingcrud.entities.AsignaturaEntity;
import com.block13testingcrud.block13testingcrud.entities.StudentEntity;
import com.block13testingcrud.block13testingcrud.exception.EntityNotFoundException;
import com.block13testingcrud.block13testingcrud.mapper.AsignaturaMapper;
import com.block13testingcrud.block13testingcrud.repository.AsignaturaRepository;
import com.block13testingcrud.block13testingcrud.repository.StudentRepository;
import com.block13testingcrud.block13testingcrud.services.AsignaturaServiceImpl;
import com.block13testingcrud.block13testingcrud.services.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AsignaturaServiceImplTest {

    @InjectMocks
    private AsignaturaServiceImpl asignaturaService;

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @Mock
    private AsignaturaMapper asignaturaMapper;

    @Mock
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void testGetAsignaturasByStudentId() {
        Integer studentId = 1;

        // Define el comportamiento esperado del repositorio
        List<AsignaturaEntity> asignaturas = new ArrayList<>();
        asignaturas.add(new AsignaturaEntity());
        Mockito.when(asignaturaRepository.findAllByStudents_IdStudent(studentId)).thenReturn(asignaturas);

        // Ejecuta el método que queremos probar
        List<AsignaturaEntity> result = asignaturaService.getAsignaturasByStudentId(studentId);

        // Verifica que el método del repositorio haya sido llamado con el parámetro correcto
        Mockito.verify(asignaturaRepository).findAllByStudents_IdStudent(studentId);

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(asignaturas, result);
    }

    @Test
    public void testCreateAsignatura() {
        // Define el objeto de entrada
        AsignaturaInputDTO inputDTO = new AsignaturaInputDTO();
        // Define el objeto de salida esperado
        AsignaturaEntity savedAsignaturaEntity = new AsignaturaEntity();
        AsignaturaInputDTO outputDTO = new AsignaturaInputDTO();

        // Define el comportamiento esperado del mapper y del repositorio
        Mockito.when(asignaturaMapper.toEntity(inputDTO)).thenReturn(savedAsignaturaEntity);
        Mockito.when(asignaturaRepository.save(savedAsignaturaEntity)).thenReturn(savedAsignaturaEntity);
        Mockito.when(asignaturaMapper.toDTO(savedAsignaturaEntity)).thenReturn(outputDTO);

        // Ejecuta el método que queremos probar
        AsignaturaInputDTO result = asignaturaService.createAsignatura(inputDTO);

        // Verifica que el método del mapper y del repositorio haya sido llamado con los parámetros correctos
        Mockito.verify(asignaturaMapper).toEntity(inputDTO);
        Mockito.verify(asignaturaRepository).save(savedAsignaturaEntity);
        Mockito.verify(asignaturaMapper).toDTO(savedAsignaturaEntity);

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(outputDTO, result);
    }

  /*  @Test(expected = RuntimeException.class)
    public void testCreateAsignaturaWithInvalidData() {
        // Define el objeto de entrada con datos inválidos
        AsignaturaInputDTO inputDTO = new AsignaturaInputDTO();
        inputDTO.setInitialDate(null); // Nombre nulo, lo cual es inválido

        // Ejecuta el método que queremos probar, esperando que lance una excepción
        asignaturaService.createAsignatura(inputDTO);
    }*/


    @Test
    public void testDeleteAsignatura() {
        // Define el id de la asignatura a eliminar
        Integer idAsignatura = 1;

        // Crea una asignatura de prueba con estudiantes asociados
        AsignaturaEntity asignatura = new AsignaturaEntity();
        asignatura.setIdAsignatura(idAsignatura);

        List<StudentEntity> students = new ArrayList<>();
        StudentEntity student1 = new StudentEntity();
        student1.setIdStudent(101);
        StudentEntity student2 = new StudentEntity();
        student2.setIdStudent(102);

        students.add(student1);
        students.add(student2);
        asignatura.setStudents(students);

        // Define el comportamiento esperado del repositorio
        Mockito.when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.of(asignatura));

        // Ejecuta el método que queremos probar
        ResponseEntity<?> responseEntity = asignaturaService.deleteAsignatura(idAsignatura);

        // Verifica que el método del repositorio haya sido llamado con el parámetro correcto
        Mockito.verify(asignaturaRepository).findById(idAsignatura);

        // Verifica que los estudiantes tengan la asignatura eliminada
        Assert.assertTrue(student1.getAsignaturas().isEmpty());
        Assert.assertTrue(student2.getAsignaturas().isEmpty());

        // Verifica que el método del repositorio haya sido llamado para guardar los estudiantes
        Mockito.verify(studentRepository, Mockito.times(2)).save(Mockito.any(StudentEntity.class));

        // Verifica que el método del repositorio haya sido llamado para eliminar la asignatura
        Mockito.verify(asignaturaRepository).delete(asignatura);

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(new ResponseEntity<>("Asignatura eliminada correctamente", HttpStatus.OK), responseEntity);
    }


    @Test(expected = RuntimeException.class)
    public void testDeleteAsignaturaNotFound() {
        // Define el id de una asignatura que no existe en la base de datos
        Integer idAsignatura = 999;

        // Define el comportamiento esperado del repositorio
        Mockito.when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.empty());

        // Ejecuta el método que queremos probar, esperando que lance una excepción
        asignaturaService.deleteAsignatura(idAsignatura);
    }

    @Test
    public void testDeleteAsignaturaWithAssociatedStudents() {
        // Define el id de la asignatura a eliminar
        Integer idAsignatura = 1;

        // Crea una asignatura de prueba con estudiantes asociados
        AsignaturaEntity asignatura = new AsignaturaEntity();
        asignatura.setIdAsignatura(idAsignatura);

        List<StudentEntity> students = new ArrayList<>();
        StudentEntity student1 = new StudentEntity();
        student1.setIdStudent(101);
        student1.getAsignaturas().add(asignatura); // Asignatura asociada al estudiante
        StudentEntity student2 = new StudentEntity();
        student2.setIdStudent(102);
        student2.getAsignaturas().add(asignatura); // Asignatura asociada al estudiante

        students.add(student1);
        students.add(student2);
        asignatura.setStudents(students);

        // Define el comportamiento esperado del repositorio
        Mockito.when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.of(asignatura));

        // Ejecuta el método que queremos probar
        ResponseEntity<?> responseEntity = asignaturaService.deleteAsignatura(idAsignatura);

        // Verifica que el método del repositorio haya sido llamado con el parámetro correcto
        Mockito.verify(asignaturaRepository).findById(idAsignatura);

        // Verifica que los estudiantes ya no tengan la asignatura asociada
        Assert.assertTrue(student1.getAsignaturas().isEmpty());
        Assert.assertTrue(student2.getAsignaturas().isEmpty());

        // Verifica que el método del repositorio haya sido llamado para guardar los estudiantes
        Mockito.verify(studentRepository, Mockito.times(2)).save(Mockito.any(StudentEntity.class));

        // Verifica que el método del repositorio haya sido llamado para eliminar la asignatura
        Mockito.verify(asignaturaRepository).delete(asignatura);

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(new ResponseEntity<>("Asignatura eliminada correctamente", HttpStatus.OK), responseEntity);
    }


    @Test
    public void testGetAllAsignaturas() {
        // Crea una lista de asignaturas de prueba
        List<AsignaturaEntity> asignaturaEntities = new ArrayList<>();
        asignaturaEntities.add(new AsignaturaEntity());
        asignaturaEntities.add(new AsignaturaEntity());

        // Define el comportamiento esperado del repositorio
        Mockito.when(asignaturaRepository.findAll()).thenReturn(asignaturaEntities);

        // Ejecuta el método que queremos probar
        List<AsignaturaInputDTO> result = asignaturaService.getAllAsignaturas();

        // Verifica que el método del repositorio haya sido llamado
        Mockito.verify(asignaturaRepository).findAll();

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(asignaturaMapper.toDTOList(asignaturaEntities), result);
    }

    @Test
    public void testGetStudentByAsignaturaId() {
        Integer idAsignatura = 1;

        // Crea una asignatura de prueba con estudiantes asociados
        AsignaturaEntity asignatura = new AsignaturaEntity();
        asignatura.setIdAsignatura(idAsignatura);

        List<StudentEntity> students = new ArrayList<>();
        StudentEntity student1 = new StudentEntity();
        student1.setIdStudent(101);
        StudentEntity student2 = new StudentEntity();
        student2.setIdStudent(102);

        students.add(student1);
        students.add(student2);
        asignatura.setStudents(students);

        // Define el comportamiento esperado del repositorio
        Mockito.when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.of(asignatura));

        // Ejecuta el método que queremos probar
        List<StudentEntity> result = asignaturaService.getStudentByAsignaturaId(idAsignatura);

        // Verifica que el método del repositorio haya sido llamado con el parámetro correcto
        Mockito.verify(asignaturaRepository).findById(idAsignatura);

        // Verifica que el resultado sea el esperado
        Assert.assertEquals(students, result);
    }


    @Test(expected = EntityNotFoundException.class)
    public void testGetStudentByAsignaturaIdNotFound() {
        // Define el id de una asignatura que no existe en la base de datos
        Integer idAsignatura = 999;

        // Define el comportamiento esperado del repositorio
        Mockito.when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.empty());

        // Ejecuta el método que queremos probar, esperando que lance una excepción
        asignaturaService.getStudentByAsignaturaId(idAsignatura);
    }



}
