package com.block13testingcrud.block13testingcrud.mappersTest;

import com.block13testingcrud.block13testingcrud.dto.input.AsignaturaInputDTO;
import com.block13testingcrud.block13testingcrud.entities.AsignaturaEntity;
import com.block13testingcrud.block13testingcrud.mapper.AsignaturaMapper;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AsignaturaMapperTest {

    private AsignaturaMapper asignaturaMapper;

    @BeforeEach
    public void setUp() {
        // Inicializa el mapper antes de cada prueba
        asignaturaMapper = new AsignaturaMapper();
    }

    @Test
    public void testToEntity() {
        // Crea un DTO de prueba
        AsignaturaInputDTO asignaturaInputDTO = new AsignaturaInputDTO();
        asignaturaInputDTO.setComents("Comentarios de prueba");

        // Mockea las fechas
        Date initialDate = Mockito.mock(Date.class);
        Date finishDate = Mockito.mock(Date.class);
        asignaturaInputDTO.setInitialDate(initialDate);
        asignaturaInputDTO.setFinishDate(finishDate);

        // Ejecuta el método que queremos probar
        AsignaturaEntity result = asignaturaMapper.toEntity(asignaturaInputDTO);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos del DTO se hayan mapeado correctamente a la entidad
        assertEquals(asignaturaInputDTO.getComents(), result.getComents());
        assertEquals(asignaturaInputDTO.getInitialDate(), result.getInitialDate());
        assertEquals(asignaturaInputDTO.getFinishDate(), result.getFinishDate());
    }

    @Test
    public void testToDTO() {
        // Crea una entidad de prueba
        AsignaturaEntity asignaturaEntity = new AsignaturaEntity();
        asignaturaEntity.setIdAsignatura(1);
        asignaturaEntity.setComents("Comentarios de prueba");

        // Mockea las fechas
        Date initialDate = Mockito.mock(Date.class);
        Date finishDate = Mockito.mock(Date.class);
        asignaturaEntity.setInitialDate(initialDate);
        asignaturaEntity.setFinishDate(finishDate);

        // Ejecuta el método que queremos probar
        AsignaturaInputDTO result = asignaturaMapper.toDTO(asignaturaEntity);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);

        // Verifica que los atributos de la entidad se hayan mapeado correctamente al DTO
        assertEquals(asignaturaEntity.getIdAsignatura(), result.getIdAsignatura());
        assertEquals(asignaturaEntity.getComents(), result.getComents());
        assertEquals(asignaturaEntity.getInitialDate(), result.getInitialDate());
        assertEquals(asignaturaEntity.getFinishDate(), result.getFinishDate());
    }

    @Test
    public void testToDTOList() {
        // Crea una lista de entidades de prueba
        List<AsignaturaEntity> asignaturaEntities = new ArrayList<>();
        AsignaturaEntity asignaturaEntity1 = new AsignaturaEntity();
        asignaturaEntity1.setIdAsignatura(1);
        asignaturaEntity1.setComents("Comentarios de prueba 1");

        // Mockea las fechas
        Date initialDate1 = Mockito.mock(Date.class);
        Date finishDate1 = Mockito.mock(Date.class);
        asignaturaEntity1.setInitialDate(initialDate1);
        asignaturaEntity1.setFinishDate(finishDate1);
        asignaturaEntities.add(asignaturaEntity1);

        AsignaturaEntity asignaturaEntity2 = new AsignaturaEntity();
        asignaturaEntity2.setIdAsignatura(2);
        asignaturaEntity2.setComents("Comentarios de prueba 2");

        // Mockea las fechas
        Date initialDate2 = Mockito.mock(Date.class);
        Date finishDate2 = Mockito.mock(Date.class);
        asignaturaEntity2.setInitialDate(initialDate2);
        asignaturaEntity2.setFinishDate(finishDate2);
        asignaturaEntities.add(asignaturaEntity2);

        // Ejecuta el método que queremos probar
        List<AsignaturaInputDTO> result = asignaturaMapper.toDTOList(asignaturaEntities);

        // Verifica que el resultado no sea nulo y tenga el tamaño correcto
        assertNotNull(result);
        assertEquals(asignaturaEntities.size(), result.size());

        // Verifica que los atributos de las entidades se hayan mapeado correctamente a los DTOs
        assertEquals(asignaturaEntity1.getIdAsignatura(), result.get(0).getIdAsignatura());
        assertEquals(asignaturaEntity1.getComents(), result.get(0).getComents());
        assertEquals(asignaturaEntity1.getInitialDate(), result.get(0).getInitialDate());
        assertEquals(asignaturaEntity1.getFinishDate(), result.get(0).getFinishDate());

        assertEquals(asignaturaEntity2.getIdAsignatura(), result.get(1).getIdAsignatura());
        assertEquals(asignaturaEntity2.getComents(), result.get(1).getComents());
        assertEquals(asignaturaEntity2.getInitialDate(), result.get(1).getInitialDate());
        assertEquals(asignaturaEntity2.getFinishDate(), result.get(1).getFinishDate());
    }
}
