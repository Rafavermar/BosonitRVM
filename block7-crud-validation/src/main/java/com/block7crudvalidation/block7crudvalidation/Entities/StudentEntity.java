package com.block7crudvalidation.block7crudvalidation.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "estudiantes")

public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id_student;
    @OneToOne
    @JoinColumn(name = "idPersona")
    PersonaEntity persona;
    @Column(name = "horaSemana")
    Integer hoursWeek;
    @Column(name = "comentarios")
    String coments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfesor")
    ProfesorEntity profesor;
    @Column(name = "rama")
    String branch;
    @OneToMany
    List<Alumnos_EstudiosEntity> estudios;
}

