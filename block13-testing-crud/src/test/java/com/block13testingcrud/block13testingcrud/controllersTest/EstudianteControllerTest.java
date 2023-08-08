package com.block13testingcrud.block13testingcrud.controllersTest;

import com.block13testingcrud.block13testingcrud.controllers.EstudianteController;
import com.block13testingcrud.block13testingcrud.dto.input.StudentInputDto;

import com.block13testingcrud.block13testingcrud.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EstudianteController.class)
public class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void agregarEstudiante_debeRetornarEstudianteAgregado() throws Exception {
        // Arrange
        StudentInputDto estudianteInput = new StudentInputDto();
        estudianteInput.setIdStudent(1);
        estudianteInput.setIdPersona(2);
        estudianteInput.setNumHoursWeek(40);
        estudianteInput.setComments("No puede asistir por las tardes");
        estudianteInput.setIdProfesor(1);
        estudianteInput.setBranch("branch");

        // Convertir el objeto a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String estudianteInputJson = objectMapper.writeValueAsString(estudianteInput);

        when(studentService.agregarEstudiante(any(StudentInputDto.class))).thenReturn(estudianteInput);

        // Act & Assert
        mockMvc.perform(post("/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(estudianteInputJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idStudent").value(1))
                .andExpect(jsonPath("$.idPersona").value(2))
                .andExpect(jsonPath("$.numHoursWeek").value(40))
                .andExpect(jsonPath("$.comments").value("No puede asistir por las tardes"))
                .andExpect(jsonPath("$.idProfesor").value(1))
                .andExpect(jsonPath("$.branch").value("branch"));
    }

    @Test
    public void eliminarEstudiante_debeRetornarMensajeCorrecto() throws Exception {
        // Arrange
        Integer idParaEliminar = 1;

        doNothing().when(studentService).deleteStudent(idParaEliminar);

        // Act & Assert
        mockMvc.perform(delete("/estudiantes/" + idParaEliminar)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Estudiante eliminado con éxito"));
    }

}

