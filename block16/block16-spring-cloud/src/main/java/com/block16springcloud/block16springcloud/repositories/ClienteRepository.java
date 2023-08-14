package com.block16springcloud.block16springcloud.repositories;


import com.block16springcloud.block16springcloud.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

