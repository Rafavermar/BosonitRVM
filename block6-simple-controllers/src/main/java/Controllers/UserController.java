package Controllers;
import Entities.Persona;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/{nombre}")
    public String getUser(@PathVariable String nombre) {
        return "Hola " + nombre;
    }

    @PostMapping("useradd")
    public Persona addUser(@RequestBody Persona persona) {
        persona.setEdad(persona.getEdad() + 1);
        return persona;
    }
}