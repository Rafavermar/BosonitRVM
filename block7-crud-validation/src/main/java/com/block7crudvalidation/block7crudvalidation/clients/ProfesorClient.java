package com.block7crudvalidation.block7crudvalidation.clients;

import com.block7crudvalidation.block7crudvalidation.dto.output.ProfesorFullOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@FeignClient(name = "profesor", url = "http://localhost:8081")
public interface ProfesorClient {
    @GetMapping("/profesores/{id}")
    ProfesorFullOutputDto getProfesor(@PathVariable Integer id, @RequestParam String outputType);
}
