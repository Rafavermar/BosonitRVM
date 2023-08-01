package com.jpacascade.jpacascade.repositories;

import com.jpacascade.jpacascade.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { }
