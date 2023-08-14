package com.block16springcloud.block16springcloud.services;

import com.block16springcloud.block16springcloud.entities.Cliente;
import com.block16springcloud.block16springcloud.entities.Viaje;
import com.block16springcloud.block16springcloud.exceptions.MaxPassengersReachedException;
import com.block16springcloud.block16springcloud.exceptions.PassengerNotFoundException;
import com.block16springcloud.block16springcloud.exceptions.ViajeNotFoundException;
import com.block16springcloud.block16springcloud.repositories.ClienteRepository;
import com.block16springcloud.block16springcloud.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Viaje> getAllViajes() {
        return viajeRepository.findAll();
    }

    @Override
    public Viaje getViajeById(Long id) {
        return viajeRepository.findById(id).orElse(null);
    }

    @Override
    public Viaje createViaje(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    @Override
    public Viaje updateViaje(Long id, Viaje updatedViaje) {
        if (viajeRepository.existsById(id)) {
            updatedViaje.setId(id);
            return viajeRepository.save(updatedViaje);
        }
        return null;
    }

    @Override
    public void deleteViaje(Long id) {
        viajeRepository.deleteById(id);
    }

    @Override
    public Viaje addPassengerToTrip(Long tripId, Long passengerId) {
        Viaje viaje = viajeRepository.findById(tripId).orElseThrow(() -> new ViajeNotFoundException("Viaje no encontrado"));
        if (viaje.getPassengers().size() >= 40) {
            throw new MaxPassengersReachedException("El viaje ya tiene 40 pasajeros");
        }

        Cliente passenger = clienteRepository.findById(passengerId).orElseThrow(() -> new PassengerNotFoundException("Pasajero no encontrado"));
        viaje.getPassengers().add(passenger);

        return viajeRepository.save(viaje);
    }

    @Override
    public int getPassengerCount(Long tripId) {
        return viajeRepository.findById(tripId).map(viaje -> viaje.getPassengers().size()).orElse(0);
    }


    @Override
    public Viaje updateTripStatus(Long tripId, String tripStatus) {
        Optional<Viaje> viajeOpt = viajeRepository.findById(tripId);
        if (viajeOpt.isPresent()) {
            Viaje viaje = viajeOpt.get();
            viaje.setStatus(tripStatus);
            return viajeRepository.save(viaje);
        }
        return null;
    }

    @Override
    public String verifyTrip(Long tripId) {
        return viajeRepository.findById(tripId).map(Viaje::getStatus).orElse("Not Found");
    }
}