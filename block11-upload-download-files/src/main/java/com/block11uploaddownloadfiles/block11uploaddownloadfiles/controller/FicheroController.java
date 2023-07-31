package com.block11uploaddownloadfiles.block11uploaddownloadfiles.controller;

import com.block11uploaddownloadfiles.block11uploaddownloadfiles.entity.Fichero;
import com.block11uploaddownloadfiles.block11uploaddownloadfiles.service.FicheroService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FicheroController {
    @Autowired
    private FicheroService ficheroService;

    @PostMapping("/upload/{tipo}")
    public Fichero uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("categoria") String categoria, @PathVariable String tipo) {
        return ficheroService.guardarFichero(file, categoria, tipo);
    }

    @GetMapping("/setpath")
    public String setPath(@RequestParam("path") String path) {
        ficheroService.setPath(path);
        return "Ruta configurada con éxito a " + path;
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(@RequestParam(required = false) Long id, @RequestParam(required = false) String nombre) {
        Resource file = (id != null) ? ficheroService.cargarFicheroPorId(id) : ficheroService.cargarFichero(nombre);
        // Retorna el fichero como respuesta
        return null;
    }

}
