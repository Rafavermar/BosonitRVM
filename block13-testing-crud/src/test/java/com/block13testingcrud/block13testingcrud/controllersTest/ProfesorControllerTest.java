package com.block13testingcrud.block13testingcrud.controllersTest;


import com.block13testingcrud.block13testingcrud.controllers.ProfesorController;
import com.block13testingcrud.block13testingcrud.dto.input.ProfesorInputDto;

import com.block13testingcrud.block13testingcrud.dto.output.ProfesorFullOutputDto;
import com.block13testingcrud.block13testingcrud.exception.EntityNotFoundException;
import com.block13testingcrud.block13testingcrud.exception.UnprocessableEntityException;
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
    private ObjectMapper objectMapper = new ObjectMapper();

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
public void obtenerProfesorPorId_FullDetails_Success() throws Exception {
    ProfesorFullOutputDto profesorFullDTO = new ProfesorFullOutputDto(); // create a real instance or a mock if needed
    when(profesorService.getProfesorFullDetailsById(anyInt())).thenReturn(profesorFullDTO);

    mockMvc.perform(get("/profesores/125?outputType=full")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists()); // You can add more detailed checks if needed
}

    @Test
    public void obtenerProfesorPorId_SimpleDetails_Success() throws Exception {
        ProfesorInputDto profesorInputDto = new ProfesorInputDto();
        when(profesorService.getProfesorDTOById(anyInt())).thenReturn(profesorInputDto);

        mockMvc.perform(get("/profesores/125")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void obtenerProfesorPorId_NotFound() throws Exception {
        String errorMessage = "La entidad con ID: 125 no fue encontrada.";
        when(profesorService.getProfesorDTOById(eq(125))).thenThrow(new EntityNotFoundException(errorMessage));

        mockMvc.perform(get("/profesores/125")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value(errorMessage));
    }

    @Test
    public void testActualizarProfesor_Success() throws Exception {
        // Datos de prueba
        Integer id = 125;
        ProfesorInputDto inputDto = new ProfesorInputDto(
                id,
                100,
                "Comentario de prueba",
                "Sede A");

        when(profesorService.updateProfesor(eq(id), any(ProfesorInputDto.class))).thenReturn(inputDto);

        mockMvc.perform(put("/profesores/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProfesor").value(id))
                .andExpect(jsonPath("$.idPersona").value(100))
                .andExpect(jsonPath("$.comments").value("Comentario de prueba"))
                .andExpect(jsonPath("$.branch").value("Sede A"));
    }
    @Test
    public void testActualizarProfesor_NotFound() throws Exception {
        Integer id = 125;
        ProfesorInputDto inputDto = new ProfesorInputDto(id, 100, "Comentario de prueba", "Sede A");

        String errorMessage = "La entidad con ID: " + id + " no fue encontrada.";

        when(profesorService.updateProfesor(eq(id), any(ProfesorInputDto.class)))
                .thenThrow(new EntityNotFoundException(errorMessage));

        mockMvc.perform(put("/profesores/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value(errorMessage));
    }

    /**
     * Test para el endpoint de actualización de un profesor. Este método verifica
     * la correcta gestión de errores cuando se produce una excepción de tipo
     * {@link UnprocessableEntityException}.
     *
     * <p>
     * En este test se define un mensaje base ("baseErrorMessage") que es el mensaje
     * específico del error (por ejemplo, "Error específico"). Luego, debido a cómo
     * se ha implementado el constructor de {@link UnprocessableEntityException},
     * el mensaje real que se genera tiene un prefijo ("Entidad no procesable: "),
     * resultando en un mensaje formateado ("formattedErrorMessage").
     * </p>
     *
     * <p>
     * El motivo de definir ambos mensajes en el test es para simular con precisión
     * la construcción del mensaje de error que ocurre en el código real y, por lo tanto,
     * establecer la expectativa correcta para la validación del test. Es esencial
     * que el test refleje la lógica real para asegurar su efectividad.
     * </p>
     *
     * @throws Exception si ocurre un error durante la ejecución del test
     */
    @Test
    public void testActualizarProfesor_UnprocessableEntity() throws Exception {
        Integer id = 125;
        ProfesorInputDto inputDto = new ProfesorInputDto(id,
                100, "Comentario de prueba",
                "Sede A");
        String baseErrorMessage = "Error específico";
        String formattedErrorMessage = "Entidad no procesable: " + baseErrorMessage;

        when(profesorService.updateProfesor(eq(id), any(ProfesorInputDto.class)))
                .thenThrow(new UnprocessableEntityException(baseErrorMessage));

        mockMvc.perform(put("/profesores/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.mensaje").value(formattedErrorMessage));
    }

}
