package com.block11uploaddownloadfiles.block11uploaddownloadfiles.service;

import com.block11uploaddownloadfiles.block11uploaddownloadfiles.entity.Fichero;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FicheroService {
    Fichero guardarFichero(MultipartFile file, String categoria, String tipo);
    Resource cargarFichero(String nombreFichero);
    Resource cargarFicheroPorId(Long id);
    void setPath(String path);
}
