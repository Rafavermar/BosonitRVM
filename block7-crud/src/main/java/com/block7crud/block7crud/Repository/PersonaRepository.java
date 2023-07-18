package com.block7crud.block7crud.Repository;

import com.block7crud.block7crud.Entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByNombre(String nombre);
}
