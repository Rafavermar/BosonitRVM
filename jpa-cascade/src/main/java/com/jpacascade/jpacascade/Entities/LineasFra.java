package com.jpacascade.jpacascade.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LineasFra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String proNomb;
    private Double cantidad;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "idFra")
    private CabeceraFra cabeceraFra;
}
