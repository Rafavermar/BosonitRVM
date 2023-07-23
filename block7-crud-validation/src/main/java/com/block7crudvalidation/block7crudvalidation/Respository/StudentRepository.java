package com.block7crudvalidation.block7crudvalidation.Respository;

import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    StudentEntity findByPersona(PersonaEntity personaEntity);
    Optional<StudentEntity> findByIdStudent(Integer idStudent);

    // Puedes eliminar el siguiente método ya que no será utilizado más.
    // @Modifying
    // @Query("UPDATE StudentEntity s SET s.profesor = NULL WHERE s.profesor.id = :profesorId")
    // void setProfesorNullForStudentsOfProfesor(@Param("profesorId") Integer profesorId);

    // @Query("SELECT s FROM StudentEntity s WHERE s.persona.idPersona = :idPersona")
    //Optional<StudentEntity> findByIdPersona(@Param("idPersona") Integer idPersona);
}
