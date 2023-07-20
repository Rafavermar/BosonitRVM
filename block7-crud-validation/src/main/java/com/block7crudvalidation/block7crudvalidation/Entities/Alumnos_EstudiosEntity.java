package com.block7crudvalidation.block7crudvalidation.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "EstudianteAsignatura")

public class Alumnos_EstudiosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer idStudy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfesor")
    ProfesorEntity profesor;
    @ManyToMany(cascade = CascadeType.ALL)
    StudentEntity student;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comment;
    @Column(name = "initialDate")
    Date initialDate;
    @Column(name = "finishDate")
    Date finishDate;
}

