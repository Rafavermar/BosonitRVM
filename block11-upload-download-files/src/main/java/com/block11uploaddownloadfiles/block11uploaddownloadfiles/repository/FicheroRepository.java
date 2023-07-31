package com.block11uploaddownloadfiles.block11uploaddownloadfiles.repository;

import com.block11uploaddownloadfiles.block11uploaddownloadfiles.entity.Fichero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FicheroRepository extends JpaRepository<Fichero, Long> {
    Optional<Fichero> findByNombreFichero(String nombreFichero);
}