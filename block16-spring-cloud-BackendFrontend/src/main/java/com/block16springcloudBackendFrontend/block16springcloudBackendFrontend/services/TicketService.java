package com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.services;

import com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.entities.Ticket;

public interface TicketService {
    Ticket generateTicket(Long userId, Long tripId);
}

