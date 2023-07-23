package com.block7crudvalidation.block7crudvalidation.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idStudent;

    @OneToOne
    @JoinColumn(name = "idPersona")
    PersonaEntity persona;

    @Column(name = "num_hours_week")
    int numHoursWeek;

    @Column(name = "comments")
    String comments;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "idProfesor", nullable = true)
    ProfesorEntity profesor;


    @NotNull
    @Column(name = "branch")
    String branch;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProfesorEstudiante> profesorEstudiantes = new HashSet<>();

}
