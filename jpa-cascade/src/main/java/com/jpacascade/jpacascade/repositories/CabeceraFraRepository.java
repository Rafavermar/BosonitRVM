package com.jpacascade.jpacascade.repositories;

import com.jpacascade.jpacascade.entities.CabeceraFra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabeceraFraRepository extends JpaRepository<CabeceraFra, Integer> { }
