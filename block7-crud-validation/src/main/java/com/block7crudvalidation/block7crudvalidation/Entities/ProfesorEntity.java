package com.block7crudvalidation.block7crudvalidation.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="profesor")
public class ProfesorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer idProfesor;

    @OneToOne
    @JoinColumn(name="idPersona")
    PersonaEntity persona;

    @Column(name="comments")
    String comments;

    @NotNull
    @Column(name= "branch")
    String branch;
}
