package com.block13testingcrud.block13testingcrud.repository;


import com.block13testingcrud.block13testingcrud.entities.AsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, Integer> {

    List<AsignaturaEntity> findAllByStudents_IdStudent(Integer idStudent);
}
