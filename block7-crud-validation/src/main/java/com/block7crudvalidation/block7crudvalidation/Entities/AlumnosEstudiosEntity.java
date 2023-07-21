package com.block7crudvalidation.block7crudvalidation.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "EstudianteAsignatura")
public class AlumnosEstudiosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idStudy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "EstudianteAsignatura_Estudiante",
            joinColumns = @JoinColumn(name = "idStudy"),
            inverseJoinColumns = @JoinColumn(name = "idStudent")
    )
    List<StudentEntity> students;

    @Column(name = "asignatura")
    String asignatura;

    @Column(name = "comentarios")
    String comment;

    @Column(name = "initialDate")
    Date initialDate;

    @Column(name = "finishDate")
    Date finishDate;
}
