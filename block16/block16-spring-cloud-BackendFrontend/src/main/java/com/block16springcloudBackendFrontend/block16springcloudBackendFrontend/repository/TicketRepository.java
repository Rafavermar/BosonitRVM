package com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.repository;

import com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
