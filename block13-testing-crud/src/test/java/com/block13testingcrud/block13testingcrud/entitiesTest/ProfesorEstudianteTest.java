package com.block13testingcrud.block13testingcrud.entitiesTest;

import com.block13testingcrud.block13testingcrud.entities.ProfesorEntity;
import com.block13testingcrud.block13testingcrud.entities.ProfesorEstudiante;
import com.block13testingcrud.block13testingcrud.entities.StudentEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfesorEstudianteTest {

    @Test
    public void testProfesorEstudiante() {
        // Arrange
        Long id = 1L;
        ProfesorEntity profesor = new ProfesorEntity();
        profesor.setIdProfesor(1);
        StudentEntity student = new StudentEntity();
        student.setIdStudent(2);

        // Act
        ProfesorEstudiante profesorEstudiante = new ProfesorEstudiante();
        profesorEstudiante.setId(id);
        profesorEstudiante.setProfesor(profesor);
        profesorEstudiante.setStudent(student);

        // Assert
        assertEquals(id, profesorEstudiante.getId());
        assertEquals(profesor, profesorEstudiante.getProfesor());
        assertEquals(student, profesorEstudiante.getStudent());
    }
// TODO arreglar estos test, investigar sobre Equals y Hashcode
    /*
    @Test
    public void testEquals() {
        // Arrange
        ProfesorEstudiante profesorEstudiante1 = new ProfesorEstudiante();
        profesorEstudiante1.setId(1L);

        ProfesorEstudiante profesorEstudiante2 = new ProfesorEstudiante();
        profesorEstudiante2.setId(1L);

        ProfesorEstudiante profesorEstudiante3 = new ProfesorEstudiante();
        profesorEstudiante3.setId(2L);

        // Act
        boolean result1 = profesorEstudiante1.equals(profesorEstudiante2);
        boolean result2 = profesorEstudiante1.equals(profesorEstudiante3);

        // Assert
        assertTrue(result1);
        assertFalse(result2);
    }
    @Test
    public void testHashCode() {
        // Arrange
        ProfesorEstudiante profesorEstudiante1 = new ProfesorEstudiante();
        profesorEstudiante1.setId(1L);

        ProfesorEstudiante profesorEstudiante2 = new ProfesorEstudiante();
        profesorEstudiante2.setId(1L);

        // Act
        int hashCode1 = profesorEstudiante1.hashCode();
        int hashCode2 = profesorEstudiante2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }
   */
}
