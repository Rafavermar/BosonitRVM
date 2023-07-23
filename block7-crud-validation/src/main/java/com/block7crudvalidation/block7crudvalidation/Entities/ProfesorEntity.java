package com.block7crudvalidation.block7crudvalidation.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "profesor")
public class ProfesorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idProfesor;

    @OneToOne
    @JoinColumn(name = "idPersona")
    PersonaEntity persona;

    @Column(name = "comments")
    String comments;

    @NotNull
    @Column(name = "branch")
    String branch;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL) // Relación uno a muchos con StudentEntity
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<StudentEntity> students = new ArrayList<>();

    @OneToMany(mappedBy = "profesor", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = false)
    private Set<ProfesorEstudiante> profesorEstudiantes = new HashSet<>();

    @OneToMany(mappedBy = "profesor")
    private Set<StudentEntity> studentEntitySet = new HashSet<>();


}
