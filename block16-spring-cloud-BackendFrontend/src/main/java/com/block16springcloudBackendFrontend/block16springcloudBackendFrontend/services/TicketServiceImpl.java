package com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.services;

import com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.entities.Cliente;
import com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.entities.Ticket;
import com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.entities.Viaje;
import com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket generateTicket(Long userId, Long tripId) {
        // Endpoints del servicio backend
        String userEndpoint = "http://localhost:8081/cliente/" + userId;
        String tripEndpoint = "http://localhost:8081/viaje/" + tripId;

        // Usando RestTemplate para obtener detalles del usuario (cliente) y del viaje
        Cliente cliente = restTemplate.getForObject(userEndpoint, Cliente.class);
        Viaje viaje = restTemplate.getForObject(tripEndpoint, Viaje.class);

        // Validar que tanto el cliente como el viaje existan
        if (cliente == null || viaje == null) {
            throw new RuntimeException("Error al obtener datos del cliente o del viaje desde el backend");
        }

        // Crear el objeto Ticket
        Ticket ticket = new Ticket();
        ticket.setPassengerId(cliente.getId());
        ticket.setPassengerName(cliente.getNombre());
        ticket.setPassengerLastname(cliente.getApellido());
        ticket.setPassengerEmail(cliente.getEmail());
        ticket.setTripOrigin(viaje.getOrigin());
        ticket.setTripDestination(viaje.getDestination());
        ticket.setDepartureDate(viaje.getDepartureDate());
        ticket.setArrivalDate(viaje.getArrivalDate());

        // Guardamos el ticket en la base de datos
        ticket = ticketRepository.save(ticket);

        // Devolvemos el ticket
        return ticket;
    }
}
