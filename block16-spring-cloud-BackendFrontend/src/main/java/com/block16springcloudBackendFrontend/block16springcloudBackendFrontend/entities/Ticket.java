package com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long passengerId;
    private String passengerName;
    private String passengerLastname;
    private String passengerEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;

}
