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

    @GetMapping
    public List<Viaje> getAllViajes() {
        return viajeService.getAllViajes();
    }

    @GetMapping("/{id}")
    public Viaje getViajeById(@PathVariable Long id) {
        return viajeService.getViajeById(id);
    }

    @PostMapping
    public Viaje createViaje(@RequestBody Viaje viaje) {
        return viajeService.createViaje(viaje);
    }

    @PutMapping("/{id}")
    public Viaje updateViaje(@PathVariable Long id, @RequestBody Viaje updatedViaje) {
        return viajeService.updateViaje(id, updatedViaje);
    }

    @DeleteMapping("/{id}")
    public void deleteViaje(@PathVariable Long id) {
        viajeService.deleteViaje(id);
    }

    @PostMapping("/addPassenger/{tripId}/{passengerId}")
    public ResponseEntity<Viaje> addPassengerToTrip(@PathVariable Long tripId, @PathVariable Long passengerId) {
        Viaje viaje = viajeService.addPassengerToTrip(tripId, passengerId);
        return new ResponseEntity<>(viaje, HttpStatus.OK);
    }

    @GetMapping("/passenger/count/{tripId}")
    public ResponseEntity<Integer> getPassengerCount(@PathVariable Long tripId) {
        int count = viajeService.getPassengerCount(tripId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PatchMapping("/{tripId}/{tripStatus}")
    public Viaje updateTripStatus(@PathVariable Long tripId, @PathVariable String tripStatus) {
        return viajeService.updateTripStatus(tripId, tripStatus);
    }

    @GetMapping("/verify/{tripId}")
    public String verifyTrip(@PathVariable Long tripId) {
        return viajeService.verifyTrip(tripId);
    }
}