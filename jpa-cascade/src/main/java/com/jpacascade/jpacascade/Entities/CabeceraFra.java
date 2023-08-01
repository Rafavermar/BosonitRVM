package com.jpacascade.jpacascade.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CabeceraFra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Cliente cliCodi;

    private Double importe;

    @OneToMany(mappedBy = "cabeceraFra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineasFra> lineas = new ArrayList<>();

}