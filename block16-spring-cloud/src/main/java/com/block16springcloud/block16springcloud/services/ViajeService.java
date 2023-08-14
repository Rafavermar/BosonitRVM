package com.block16springcloud.block16springcloud.services;


import com.block16springcloud.block16springcloud.entities.Viaje;

import java.util.List;
public interface ViajeService {
    List<Viaje> getAllViajes();
    Viaje getViajeById(Long id);
    Viaje createViaje(Viaje viaje);
    Viaje updateViaje(Long id, Viaje updatedViaje);
    void deleteViaje(Long id);
    Viaje addPassengerToTrip(Long tripId, Long passengerId);
    int getPassengerCount(Long tripId);
    Viaje updateTripStatus(Long tripId, String tripStatus);
    String verifyTrip(Long tripId);
}
