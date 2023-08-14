package com.block16springcloud.block16springcloud.controllers;


import com.block16springcloud.block16springcloud.entities.Viaje;
import com.block16springcloud.block16springcloud.repositories.ViajeRepository;
import com.block16springcloud.block16springcloud.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/trip")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Viaje> getAllViajes() {
        return viajeService.getAllViajes();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Viaje getViajeById(@PathVariable Long id) {
        return viajeService.getViajeById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Viaje createViaje(@RequestBody Viaje viaje) {
        return viajeService.createViaje(viaje);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Viaje updateViaje(@PathVariable Long id, @RequestBody Viaje updatedViaje) {
        return viajeService.updateViaje(id, updatedViaje);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteViaje(@PathVariable Long id) {
        viajeService.deleteViaje(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/addPassenger/{tripId}/{passengerId}")
    public Viaje addPassengerToTrip(@PathVariable Long tripId, @PathVariable Long passengerId) {
        return viajeService.addPassengerToTrip(tripId, passengerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/passenger/count/{tripId}")
    public Integer getPassengerCount(@PathVariable Long tripId) {
        return viajeService.getPassengerCount(tripId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{tripId}/{tripStatus}")
    public Viaje updateTripStatus(@PathVariable Long tripId, @PathVariable String tripStatus) {
        return viajeService.updateTripStatus(tripId, tripStatus);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/verify/{tripId}")
    public String verifyTrip(@PathVariable Long tripId) {
        return viajeService.verifyTrip(tripId);
    }
}
