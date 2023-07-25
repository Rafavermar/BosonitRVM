package com.block7crudvalidation.block7crudvalidation.Respository;

import com.block7crudvalidation.block7crudvalidation.Entities.AsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, Integer> {

    List<AsignaturaEntity> findAllByStudent_IdStudent(Integer idStudent);
}
