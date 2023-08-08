package com.block13testingcrud.block13testingcrud.controllersTest;


import com.block13testingcrud.block13testingcrud.controllers.ProfesorController;
import com.block13testingcrud.block13testingcrud.dto.input.ProfesorInputDto;

import com.block13testingcrud.block13testingcrud.dto.output.ProfesorFullOutputDto;
import com.block13testingcrud.block13testingcrud.exception.EntityNotFoundException;
import com.block13testingcrud.block13testingcrud.mapper.ProfesorMapper;
import com.block13testingcrud.block13testingcrud.services.PersonaService;
import com.block13testingcrud.block13testingcrud.services.ProfesorService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfesorController.class)
public class ProfesorControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfesorMapper profesorMapper;
    @MockBean
    private PersonaService personaService;

    @MockBean
    private ProfesorService profesorService;
    @Autowired
    private ProfesorController profesorController;


    @Test
    public void agregarProfesor_cuandoExito_debeRetornarProfesorCreado() throws Exception {
        // Arrange
        ProfesorInputDto profesorInputDto = new ProfesorInputDto();
        profesorInputDto.setIdPersona(1);
        profesorInputDto.setComments("comentarioo");
        profesorInputDto.setBranch("Nombre de la rama");

        // Convertimos el DTO a JSON para enviarlo en el request
        ObjectMapper objectMapper = new ObjectMapper();
        String profesorInputJson = objectMapper.writeValueAsString(profesorInputDto);

        // Configuramos el mock para que retorne nuestro DTO cuando se llame al servicio
        when(profesorService.createProfesor(any(ProfesorInputDto.class))).thenReturn(profesorInputDto);

        // Act & Assert
        mockMvc.perform(post("/profesores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profesorInputJson))  // Usamos el JSON generado desde nuestro DTO
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idProfesor").value(profesorInputDto.getIdProfesor())) // Aquí usamos el valor de nuestro DTO en la verificación
                .andExpect(jsonPath("$.idPersona").value(profesorInputDto.getIdPersona()))
                .andExpect(jsonPath("$.comments").value(profesorInputDto.getComments()))
                .andExpect(jsonPath("$.branch").value(profesorInputDto.getBranch()));
    }

    @Test
    public void eliminarProfesor_conIdValido_debeRetornarMensajeExitoso() throws Exception {
        // Arrange
        Integer idProfesor = 123;



        doNothing().when(profesorService).deleteProfesor(idProfesor);

        // Act & Assert
        mockMvc.perform(delete("/profesores/" + idProfesor))
                .andExpect(content().string("Profesor eliminado con éxito"));
    }


    @Test
    public void obtenerProfesorPorId_conOutputTypeFull_debeRetornarProfesorCompleto() throws Exception {
        // Arrange
        Integer idProfesor = 123;
        ProfesorFullOutputDto profesorFullDTO = new ProfesorFullOutputDto();
        // Ajusta los valores de 'profesorFullDTO' según tus necesidades

        when(profesorService.getProfesorFullDetailsById(idProfesor)).thenReturn(profesorFullDTO);

        // Act & Assert
        mockMvc.perform(get("/profesores/" + idProfesor).param("outputType", "full"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // Agrega verificaciones adicionales si es necesario, como jsonPath
        ;
    }

    @Test
    public void obtenerProfesorPorId_conOutputTypeSimple_debeRetornarProfesorSimple() throws Exception {
        // Arrange
        Integer idProfesor = 124;
        ProfesorInputDto profesorInputDto = new ProfesorInputDto();
        // Ajusta los valores de 'profesorInputDto' según tus necesidades

        when(profesorService.getProfesorDTOById(idProfesor)).thenReturn(profesorInputDto);

        // Act & Assert
        mockMvc.perform(get("/profesores/" + idProfesor))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // Agrega verificaciones adicionales si es necesario, como jsonPath
        ;
    }

    @Test
    public void testObtenerProfesorPorId_NotFound() throws Exception {
        // Dado (Given)
        Integer id = 125;

        // Hacer que el mock lance la excepción cuando el método se llame con el ID 125
        when(profesorService.getProfesorDTOById(eq(125))).thenThrow(new EntityNotFoundException("La entidad con ID: 125 no fue encontrada."));


        // Ejecutar el request y verificar el resultado
        mockMvc.perform(get("/profesores/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("La entidad con ID: " + id + " no fue encontrada."));
    }




}
