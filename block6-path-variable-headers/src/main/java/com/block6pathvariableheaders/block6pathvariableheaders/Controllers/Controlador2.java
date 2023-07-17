package com.block6pathvariableheaders.block6pathvariableheaders.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    @GetMapping("/getPersona/{id}")
    public String getPersona(@PathVariable("id") String id) {
        return id;
    }
}
