package com.block7crudvalidation.block7crudvalidation.Respository;

import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
}

