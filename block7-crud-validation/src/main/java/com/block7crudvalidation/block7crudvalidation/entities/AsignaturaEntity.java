package com.block7crudvalidation.block7crudvalidation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asignaturas")
public class AsignaturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsignatura;

    @Column(name = "coments")
    private String coments;

    @Column(name = "initial_date", nullable = false)
    private LocalDate initialDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    @ManyToMany(mappedBy = "asignaturas")
    private List<StudentEntity> students = new ArrayList<>();



}
