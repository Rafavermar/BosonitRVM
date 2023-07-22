package com.block7crudvalidation.block7crudvalidation.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "idProfesor")
    ProfesorEntity profesor;

    @NotNull
    @Column(name = "branch")
    String branch;
}
