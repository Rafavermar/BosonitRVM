package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.EntityByNameNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Exception.UnprocessableEntityException;
import com.block7crudvalidation.block7crudvalidation.Mapper.PersonaMapper;
import com.block7crudvalidation.block7crudvalidation.Respository.PersonaRepository;
import com.block7crudvalidation.block7crudvalidation.Respository.ProfesorEstudianteRepository;
import com.block7crudvalidation.block7crudvalidation.Respository.ProfesorRepository;
import com.block7crudvalidation.block7crudvalidation.Respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final ProfesorRepository profesorRepository;

    private final StudentRepository studentRepository;

    private final ProfesorEstudianteRepository profesorEstudianteRepository;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository, ProfesorRepository profesorRepository,
                              StudentRepository studentRepository,
                              ProfesorEstudianteRepository profesorEstudianteRepository) {
        this.personaRepository = personaRepository;
        this.profesorRepository = profesorRepository;
        this.studentRepository = studentRepository;
        this.profesorEstudianteRepository = profesorEstudianteRepository;
    }

    @Override
    public PersonaEntity agregarPersona(PersonaEntity personaEntity) {
        // Validar los campos requeridos y lanzar UnprocessableEntityException en caso de que no cumplan los requisitos
        if (personaEntity.getUsuario() == null || personaEntity.getName() == null || personaEntity.getCity() == null) {
            throw new UnprocessableEntityException("Todos los campos (usuario, name, city) deben estar presentes y no pueden estar vacíos.");
        }

        return personaRepository.save(personaEntity);
    }

    @Override
    public PersonaEntity buscarPorId(Integer id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public PersonaEntity buscarPorUsuario(String usuario) {
        return personaRepository.findByUsuario(usuario)
                .orElseThrow(() -> new EntityByNameNotFoundException(usuario));
    }

    @Override
    public List<PersonaEntity> mostrarTodos() {
        return personaRepository.findAll();
    }
    @Override
    @Transactional
    public void borrarPersona(Integer id) {
        // Primero, busca la PersonaEntity
        PersonaEntity personaEntity = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona with id " + id + " not found"));

        // Eliminar las relaciones en la tabla profesor_estudiante que contengan a esta persona
        StudentEntity studentEntity = studentRepository.findByPersona(personaEntity);
        if (studentEntity != null) {
            profesorEstudianteRepository.deleteByStudent(studentEntity);
            studentRepository.delete(studentEntity);
        }

        // Busca cualquier ProfesorEntity relacionado
        ProfesorEntity profesorEntity = profesorRepository.findByPersona(personaEntity);
        if (profesorEntity != null) {
            // Eliminar los estudiantes relacionados con el profesor
            List<StudentEntity> students = profesorEntity.getStudents();
            for (StudentEntity relatedStudent : students) {
                studentRepository.delete(relatedStudent);
            }
            // Ahora puedes eliminar el ProfesorEntity
            profesorRepository.delete(profesorEntity);
        }

        // Finalmente, elimina la PersonaEntity
        personaRepository.delete(personaEntity);
    }





    @Override
    public PersonaEntity modificarPersona(int id, PersonaEntity personaEntity) {
        // Realizar validaciones de los campos en personaEntity y lanzar UnprocessableEntityException si no se cumplen los requisitos
        if (personaEntity.getUsuario() == null || personaEntity.getUsuario().isEmpty() ||
                personaEntity.getName() == null || personaEntity.getName().isEmpty() ||
                personaEntity.getCity() == null || personaEntity.getCity().isEmpty()) {
            throw new UnprocessableEntityException("Todos los campos (usuario, name, city) deben estar presentes y no pueden estar vacíos.");
        }

        // Recuperar la entidad existente de la base de datos
        PersonaEntity personaActual = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        // Actualizar los campos con los valores de personaEntity
        personaActual.setUsuario(personaEntity.getUsuario());
        personaActual.setPassword(personaEntity.getPassword());
        personaActual.setName(personaEntity.getName());
        personaActual.setSurname(personaEntity.getSurname());
        personaActual.setCompanyEmail(personaEntity.getCompanyEmail());
        personaActual.setPersonalEmail(personaEntity.getPersonalEmail());
        personaActual.setCity(personaEntity.getCity());
        personaActual.setActive(personaEntity.isActive());
        personaActual.setCreatedDate(personaEntity.getCreatedDate());
        personaActual.setImageUrl(personaEntity.getImageUrl());
        personaActual.setTerminationDate(personaEntity.getTerminationDate());

        // Guardar la entidad actualizada en la base de datos
        return personaRepository.save(personaActual);
    }
}
