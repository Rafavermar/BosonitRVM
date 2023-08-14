package com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.controllers;

import com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.entities.Ticket;

import com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/generateTicket/{userId}/{tripId}")
    public Ticket generateTicket(@PathVariable Long userId, @PathVariable Long tripId) {
        return ticketService.generateTicket(userId, tripId);
    }
}
