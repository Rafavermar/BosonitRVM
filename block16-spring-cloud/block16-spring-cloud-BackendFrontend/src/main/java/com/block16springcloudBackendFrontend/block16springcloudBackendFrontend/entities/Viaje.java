package com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Viaje {
    private Long id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;

}
