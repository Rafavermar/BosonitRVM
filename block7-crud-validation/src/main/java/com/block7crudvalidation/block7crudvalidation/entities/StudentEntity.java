package com.block7crudvalidation.block7crudvalidation.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// TODO quitar el hashSet en SetProfesorEstudiante
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


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_asignatura",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_asignatura"))
    private List<AsignaturaEntity> asignaturas = new ArrayList<>();



}
