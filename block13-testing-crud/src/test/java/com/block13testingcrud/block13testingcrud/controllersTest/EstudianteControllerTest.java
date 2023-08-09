package com.block13testingcrud.block13testingcrud.controllersTest;

import com.block13testingcrud.block13testingcrud.controllers.EstudianteController;
import com.block13testingcrud.block13testingcrud.dto.input.StudentInputDto;

import com.block13testingcrud.block13testingcrud.dto.output.EstudianteFullOutDto;
import com.block13testingcrud.block13testingcrud.dto.output.EstudianteSimpleOutDto;
import com.block13testingcrud.block13testingcrud.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(EstudianteController.class)
public class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;


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

    /**
     * Test para verificar el endpoint que recupera todos los estudiantes con detalles simples.
     * Se utiliza una lista mockeada de estudiantes simples para emular el comportamiento esperado del servicio.
     * La lista contiene dos entradas para simular un escenario donde hay múltiples estudiantes disponibles.
     */
    @Test
    public void obtenerTodosLosEstudiantes_SimpleOutput() throws Exception {
        List<EstudianteSimpleOutDto> simpleDtoList = Arrays.asList(new EstudianteSimpleOutDto(/*...*/), new EstudianteSimpleOutDto(/*...*/));

        when(studentService.getAllStudentsSimpleDetails()).thenReturn(simpleDtoList);

        mockMvc.perform(get("/estudiantes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void obtenerTodosLosEstudiantes_FullOutput() throws Exception {
        List<EstudianteFullOutDto> fullDtoList = Arrays.asList(new EstudianteFullOutDto(/*...*/), new EstudianteFullOutDto(/*...*/));

        when(studentService.getStudentFullDetails()).thenReturn(fullDtoList);

        mockMvc.perform(get("/estudiantes?outputType=full")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void agregarEstudiante_Created() throws Exception {
        StudentInputDto inputDto = new StudentInputDto(/*...*/);
        StudentInputDto returnedDto = new StudentInputDto(/*...*/);

        when(studentService.agregarEstudiante(any(StudentInputDto.class))).thenReturn(returnedDto);

        mockMvc.perform(post("/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void obtenerEstudiantePorId_SimpleOutput() throws Exception {
        Integer id = 125;
        StudentInputDto studentInputDto = new StudentInputDto(/*...*/);

        when(studentService.getStudentDTOById(id)).thenReturn(studentInputDto);


        mockMvc.perform(get("/estudiantes/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getEstudiantesByName_SimpleOutput() throws Exception {
        String name = "Juan";
        List<StudentInputDto> simpleDtoList = Arrays.asList(new StudentInputDto(/*...*/), new StudentInputDto(/*...*/));

        when(studentService.getStudentsDTOByName(name)).thenReturn(simpleDtoList);

        mockMvc.perform(get("/estudiantes/nombre/" + name)
                        .param("outputType", "simple")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getEstudiantesByName_FullOutput() throws Exception {
        String name = "Juan";
        List<EstudianteFullOutDto> fullDtoList = Arrays.asList(new EstudianteFullOutDto(/*...*/), new EstudianteFullOutDto(/*...*/));

        when(studentService.getStudentFullDetailsByName(name)).thenReturn(fullDtoList);

        mockMvc.perform(get("/estudiantes/nombre/" + name)
                        .param("outputType", "full")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void actualizarEstudiante() throws Exception {
        Integer id = 123;
        StudentInputDto studentInputDto = new StudentInputDto(id, 1, 20, "Algunos comentarios", 5, "Ciencias");
        StudentInputDto returnedDto = new StudentInputDto(id, 1, 25, "Comentarios actualizados", 6, "Artes");

        when(studentService.agregarEstudiante(any(StudentInputDto.class))).thenReturn(returnedDto);

        mockMvc.perform(put("/estudiantes/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentInputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").value(returnedDto.getIdStudent()))
                .andExpect(jsonPath("$.idPersona").value(returnedDto.getIdPersona()))
                .andExpect(jsonPath("$.numHoursWeek").value(returnedDto.getNumHoursWeek()))
                .andExpect(jsonPath("$.comments").value(returnedDto.getComments()))
                .andExpect(jsonPath("$.idProfesor").value(returnedDto.getIdProfesor()))
                .andExpect(jsonPath("$.branch").value(returnedDto.getBranch()));
    }




}

