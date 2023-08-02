package com.block13testingcrud.block13testingcrud.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profesor_estudiante")
public class ProfesorEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private ProfesorEntity profesor;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private StudentEntity student;

}

