package com.block10dockerizeapp.block10dockerizeapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
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
    private Date initialDate;

    @Column(name = "finish_date")
    private Date finishDate;

    @ManyToMany(mappedBy = "asignaturas")
    private List<StudentEntity> students = new ArrayList<>();



}
