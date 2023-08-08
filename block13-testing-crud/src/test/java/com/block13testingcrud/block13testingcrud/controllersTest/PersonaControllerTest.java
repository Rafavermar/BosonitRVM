package com.block13testingcrud.block13testingcrud.controllersTest;

import com.block13testingcrud.block13testingcrud.controllers.PersonaController;
import com.block13testingcrud.block13testingcrud.dto.input.PersonaInputDto;
import com.block13testingcrud.block13testingcrud.entities.PersonaEntity;
import com.block13testingcrud.block13testingcrud.exception.UnprocessableEntityException;
import com.block13testingcrud.block13testingcrud.mapper.PersonaMapper;
import com.block13testingcrud.block13testingcrud.services.PersonaService;
import com.block13testingcrud.block13testingcrud.services.ProfesorService;
import com.block13testingcrud.block13testingcrud.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PersonaControllerTest {

    @Mock
    private PersonaService personaService;

    @Mock
    private PersonaMapper personaMapper;

    @Mock
    private StudentService studentService;

    @Mock
    private ProfesorService profesorService;

    @InjectMocks
    private PersonaController personaController;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personaController).build();
    }


    @Test
    public void agregarPersona_withValidData_shouldReturnPersonaDto() throws Exception {

        // Datos de prueba
        Integer mockId = 1;

        // Puedes utilizar SimpleDateFormat si deseas ajustar el formato.
        Date createdDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-19");
        Date terminationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-31");

        PersonaInputDto personaInputDtoMock = new PersonaInputDto(
                mockId,
                "alum1",
                "1234",
                "Ann",
                "Doe",
                "Ann.doe@company.com",
                "Ann.doe@example.com",
                "New York",
                true,
                createdDate,
                "https://example.com/john_doe.jpg",
                terminationDate
        );

        PersonaEntity personaEntityMock = new PersonaEntity();
        // Asegúrate de que tu entidad también tenga los campos necesarios seteados

        when(personaMapper.toEntity(any())).thenReturn(personaEntityMock);
        when(personaService.agregarPersona(personaEntityMock)).thenReturn(personaEntityMock);
        when(personaMapper.toDTO(personaEntityMock)).thenReturn(personaInputDtoMock);

        long createdDateMillis = new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-19").getTime();
        long terminationDateMillis = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-31").getTime();

        // Este es el cuerpo de tu solicitud
        String requestBody = "{"
                + "\"usuario\": \"alum1\","
                + "\"password\": \"1234\","
                + "\"name\": \"Ann\","
                + "\"surname\": \"Doe\","
                + "\"companyEmail\": \"Ann.doe@company.com\","
                + "\"personalEmail\": \"Ann.doe@example.com\","
                + "\"city\": \"New York\","
                + "\"active\": true,"
                + "\"created_date\": \"2023-07-19T00:00:00.000+00:00\","
                + "\"imagenUrl\": \"https://example.com/john_doe.jpg\","
                + "\"termination_date\": \"2023-12-31T00:00:00.000+00:00\""
                + "}";


        // Definir el cuerpo esperado de la respuesta
        String expectedResponseBody= "{"
                + "\"id\": 1,"
                + "\"usuario\": \"alum1\","
                + "\"password\": \"1234\","
                + "\"name\": \"Ann\","
                + "\"surname\": \"Doe\","
                + "\"companyEmail\": \"Ann.doe@company.com\","
                + "\"personalEmail\": \"Ann.doe@example.com\","
                + "\"city\": \"New York\","
                + "\"active\": true,"
                + "\"created_date\": " + createdDateMillis + ","
                + "\"imagenUrl\": \"https://example.com/john_doe.jpg\","
                + "\"termination_date\": " + terminationDateMillis
                + "}";


        // Ejecutar solicitud POST
        mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResponseBody));

        verify(personaService).agregarPersona(personaEntityMock);
    }


    @Test
    public void agregarPersona_withInvalidData_shouldReturnUnprocessableEntity() throws Exception {
        String invalidRequestBody = "{\n" +
                "    \"usuario\": \"alum1\",\n" +
                "    \"password\": \"1234\",\n" +
                "    \"name\": \"Ann\",\n" +
                "    \"surname\": \"Doe\",\n" +
                "    \"companyEmail\": \"Ann.doe@company.com\",\n" +
                "    \"personalEmail\": \"Ann.doe@example.com\",\n" +
                "    \"city\": \"New York\",\n" +
                "    \"active\": true,\n" +
                "    \"created_date\": \"2023-07-19T00:00:00.000+00:00\",\n" +
                "    \"imagenUrl\": \"https://example.com/john_doe.jpg\",\n" +
                "    \"termination_date\": \"2023-12-31T00:00:00.000+00:00\"\n" +
                "}\n";  // Tu JSON inválido o que causaría el error aquí.

        when(personaService.agregarPersona(any())).thenThrow(new UnprocessableEntityException("El usuario ya existe"));


        String expectedErrorMessage = "Entidad no procesable: El usuario ya existe";
        String expectedErrorBody = "{"
                + "\"timestamp\": " + System.currentTimeMillis() + ","
                + "\"httpCode\": 422,"  // Código para UNPROCESSABLE_ENTITY
                + "\"mensaje\": \"" + expectedErrorMessage + "\""
                + "}";

        mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestBody))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.httpCode").value(422))
                .andExpect(jsonPath("$.mensaje").value("Entidad no procesable: El usuario ya existe"));
    }




}

