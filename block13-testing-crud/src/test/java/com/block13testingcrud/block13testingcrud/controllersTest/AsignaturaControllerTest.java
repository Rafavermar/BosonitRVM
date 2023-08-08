package com.block13testingcrud.block13testingcrud.controllersTest;

import com.block13testingcrud.block13testingcrud.controllers.AsignaturaController;
import com.block13testingcrud.block13testingcrud.dto.input.AsignaturaInputDTO;
import com.block13testingcrud.block13testingcrud.entities.AsignaturaEntity;
import com.block13testingcrud.block13testingcrud.entities.StudentEntity;
import com.block13testingcrud.block13testingcrud.mapper.AsignaturaMapper;
import com.block13testingcrud.block13testingcrud.services.AsignaturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AsignaturaControllerTest {


    private MockMvc mockMvc;

    @Mock
    private AsignaturaService asignaturaService;

    @InjectMocks
    private AsignaturaController asignaturaController;
    @Mock
    private AsignaturaMapper asignaturaMapper;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(asignaturaController).build();
    }


    @Test
    public void obtenerTodasLasAsignaturas_debeRetornarListaDeAsignaturas() throws Exception {
        // Arrange
        List<AsignaturaInputDTO> asignaturas = Arrays.asList(
                new AsignaturaInputDTO(1, "Comentario 1", new Date(), new Date()),
                new AsignaturaInputDTO(2, "Comentario 2", new Date(), new Date())
        );
        when(asignaturaService.getAllAsignaturas()).thenReturn(asignaturas);

        // Act & Assert
        mockMvc.perform(get("/asignaturas/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idAsignatura").value(1))
                .andExpect(jsonPath("$[0].coments").value("Comentario 1"))
                .andExpect(jsonPath("$[1].idAsignatura").value(2))
                .andExpect(jsonPath("$[1].coments").value("Comentario 2"));
    }
// TODO resolver estos tests
    /*
    @Test
    public void getAsignaturasByStudentId_debeRetornarListaDeAsignaturas() throws Exception {
        // Arrange
        Integer idStudent = 1;

        StudentEntity student1 = new StudentEntity();
        student1.setIdStudent(idStudent);

        StudentEntity student2 = new StudentEntity();
        student2.setIdStudent(idStudent + 1); // Use a different ID for the second student

        List<AsignaturaEntity> asignaturas = Arrays.asList(
                new AsignaturaEntity(1, "Comentario 1", new Date(), new Date(), Arrays.asList(student1)),
                new AsignaturaEntity(2, "Comentario 2", new Date(), new Date(), Arrays.asList(student2))
        );
        when(asignaturaService.getAsignaturasByStudentId(idStudent)).thenReturn(asignaturas);

        // Act & Assert
        mockMvc.perform(get("/asignaturas/student/{idStudent}", idStudent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idAsignatura").value(1))
                .andExpect(jsonPath("$[0].coments").value("Comentario 1"))
                .andExpect(jsonPath("$[0].initialDate").isNotEmpty())
                .andExpect(jsonPath("$[0].finishDate").isNotEmpty())
                .andExpect(jsonPath("$[0].students[0].idStudent").value(idStudent))
                .andExpect(jsonPath("$[1].idAsignatura").value(2))
                .andExpect(jsonPath("$[1].coments").value("Comentario 2"))
                .andExpect(jsonPath("$[1].initialDate").isNotEmpty())
                .andExpect(jsonPath("$[1].finishDate").isNotEmpty())
                .andExpect(jsonPath("$[1].students[0].idStudent").value(idStudent + 1)); // Use the ID for the second student

        // Verify that the service method was called with the correct idStudent argument
        verify(asignaturaService).getAsignaturasByStudentId(idStudent);
    }
*/
/*
    @Test
    public void getAsignaturasByStudentId_debeRetornar404SiElEstudianteNoExiste() throws Exception {
        // Arrange
        Integer idStudent = 1;
        when(asignaturaService.getAsignaturasByStudentId(idStudent)).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/student/{idStudent}", idStudent))
                .andExpect(status().isNotFound());

        // Verify that the service method was called with the correct idStudent argument
        verify(asignaturaService).getAsignaturasByStudentId(idStudent);
    }
    */

}

