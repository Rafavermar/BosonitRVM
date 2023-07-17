package com.block6pathvariableheaders.block6pathvariableheaders.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador4")
public class Controlador4 {
    @GetMapping("/header")
    public ResponseEntity<String> header(@RequestHeader(value = "h1", required = false) String h1,
                                         @RequestHeader(value = "h2", required = false) String h2) {
        StringBuilder response = new StringBuilder();
        if (h1 != null) {
            response.append("h1: ").append(h1).append("\n");
        }
        if (h2 != null) {
            response.append("h2: ").append(h2).append("\n");
        }
        return ResponseEntity.ok(response.toString());
    }
}
