package com.block16springcloudBackendFrontend.block16springcloudBackendFrontend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;

}
