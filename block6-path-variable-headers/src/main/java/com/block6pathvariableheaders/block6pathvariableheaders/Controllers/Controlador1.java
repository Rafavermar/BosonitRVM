package com.block6pathvariableheaders.block6pathvariableheaders.Controllers;

import com.block6pathvariableheaders.block6pathvariableheaders.Entities.Persona;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    @PostMapping("/addPersona")
    public Persona addPersona(@RequestBody Persona persona) {
        return persona;
    }
}
