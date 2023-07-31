package com.block11uploaddownloadfiles.block11uploaddownloadfiles.service;

import com.block11uploaddownloadfiles.block11uploaddownloadfiles.entity.Fichero;
import com.block11uploaddownloadfiles.block11uploaddownloadfiles.exceptions.FileTypeException;
import com.block11uploaddownloadfiles.block11uploaddownloadfiles.repository.FicheroRepository;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class FicheroServiceImpl implements FicheroService {
    private Path rootLocation; // Actualizado para permitir cambiar la ubicación

    @Autowired
    private FicheroRepository ficheroRepository;

    // Constructor para configurar la ruta por defecto
    public FicheroServiceImpl(@Value("${file.storage.path:./}") String defaultPath) {
        this.rootLocation = Paths.get(defaultPath);
        try {
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de almacenamiento");
        }
    }

    @Override
    public Fichero guardarFichero(MultipartFile file, String categoria, String tipo) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (!filename.endsWith(tipo)) {
            throw new FileTypeException("Tipo de archivo no permitido");
        }

        // Guardar el archivo en el sistema de archivos
        try {
            Path destinationFile = this.rootLocation.resolve(filename);
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo");
        }

        // Guardar el archivo en la base de datos
        Fichero fichero = new Fichero();
        fichero.setNombreFichero(filename);
        fichero.setFechaSubida(new Date());
        fichero.setCategoria(categoria);

        return ficheroRepository.save(fichero);
    }

    @Override
    public Resource cargarFichero(String nombreFichero) {
        try {
            Path file = rootLocation.resolve(nombreFichero);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Error al leer el archivo");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al leer el archivo");
        }
    }

    @Override
    public Resource cargarFicheroPorId(Long id) {
        Fichero fichero = ficheroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        return cargarFichero(fichero.getNombreFichero());
    }

    @Override
    public void setPath(String path) {
        this.rootLocation = Paths.get(path);
        try {
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de almacenamiento");
        }
    }
}
