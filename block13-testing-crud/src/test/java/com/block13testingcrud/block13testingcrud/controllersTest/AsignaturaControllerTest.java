package com.block13testingcrud.block13testingcrud.controllersTest;

import com.block13testingcrud.block13testingcrud.controllers.AsignaturaController;
import com.block13testingcrud.block13testingcrud.dto.input.AsignaturaInputDTO;
import com.block13testingcrud.block13testingcrud.entities.AsignaturaEntity;
import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.entities.ProfesorEntity;
import com.block13testingcrud.block13testingcrud.entities.StudentEntity;
import com.block13testingcrud.block13testingcrud.exception.EntityNotFoundException;
import com.block13testingcrud.block13testingcrud.mapper.AsignaturaMapper;
import com.block13testingcrud.block13testingcrud.services.AsignaturaService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
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

    @Autowired
    private ObjectMapper objectMapper;
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

    @Test
    public void getAsignaturasByStudentId_ReturnsList() throws Exception {
        // 1. Configurar los datos mockeados
        Integer idStudent = 125;

        List<AsignaturaEntity> mockAsignaturas = Arrays.asList(
                new AsignaturaEntity(/*...*/),
                new AsignaturaEntity(/*...*/)
        );

        List<AsignaturaInputDTO> expectedDTOs = Arrays.asList(
                new AsignaturaInputDTO(/*...*/),
                new AsignaturaInputDTO(/*...*/)
        );

        // 2. Configurar el comportamiento mockeado del servicio y del mapper
        when(asignaturaService.getAsignaturasByStudentId(idStudent)).thenReturn(mockAsignaturas);
        when(asignaturaMapper.toDTOList(mockAsignaturas)).thenReturn(expectedDTOs);

        // 3. Realizar la petición con mockMvc
        mockMvc.perform(get("/asignaturas/student/" + idStudent)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        // Aquí puedes agregar más verificaciones si es necesario
    }

    @Test
    public void getAsignaturasByStudentId_StudentNotFound() throws Exception {
        // 1. Configurar los datos mockeados
        Integer idStudent = 125;

        // 2. Configurar el comportamiento mockeado del servicio para lanzar una excepción
        when(asignaturaService.getAsignaturasByStudentId(idStudent))
                .thenThrow(new EntityNotFoundException("Estudiante no encontrado"));

        // 3. Realizar la petición con mockMvc y esperar un error
        mockMvc.perform(get("/asignaturas/student/" + idStudent)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()) // 404 Not Found
                .andExpect(jsonPath("$.mensaje").value("Estudiante no encontrado"));
    }

    @Test
    public void deleteAsignatura() throws Exception {
        Integer id = 123;

        when(asignaturaService.deleteAsignatura(id)).thenReturn(ResponseEntity.ok().build());  // Asume que retorna un ResponseEntity sin contenido.

        mockMvc.perform(delete("/asignaturas/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void createAsignatura() throws Exception {
        AsignaturaInputDTO inputDTO = new AsignaturaInputDTO(null, "Comentarios", new Date(), new Date());
        AsignaturaInputDTO returnedDTO = new AsignaturaInputDTO(123, "Comentarios", new Date(), new Date());

        when(asignaturaService.createAsignatura(any(AsignaturaInputDTO.class))).thenReturn(returnedDTO);

        mockMvc.perform(post("/asignaturas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idAsignatura").value(returnedDTO.getIdAsignatura()))
                .andExpect(jsonPath("$.coments").value(returnedDTO.getComents()));
    }

}