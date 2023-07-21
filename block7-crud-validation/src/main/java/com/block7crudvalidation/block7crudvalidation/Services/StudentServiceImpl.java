package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.DTO.Output.EstudianteFullDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.Respository.PersonaRepository;
import com.block7crudvalidation.block7crudvalidation.Respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public StudentEntity saveStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public EstudianteFullDTO getStudentFullDetails(Integer id) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(id);
        return studentOptional.map(studentEntity -> {
            EstudianteFullDTO studentFullDTO = new EstudianteFullDTO();
            studentFullDTO.setIdStudent(studentEntity.getIdStudent());
            studentFullDTO.setNumHoursWeek(studentEntity.getNumHoursWeek());
            studentFullDTO.setComments(studentEntity.getComments());

            // Fetch the associated PersonaEntity
            Integer idPersona = studentEntity.getPersona().getIdPersona();
            Optional<PersonaEntity> personaOptional = personaRepository.findById(idPersona);
            if (personaOptional.isPresent()) {
                PersonaEntity personaEntity = personaOptional.get();
                studentFullDTO.setIdPersona(personaEntity.getIdPersona());
                studentFullDTO.setUsuario(personaEntity.getUsuario());
                studentFullDTO.setName(personaEntity.getName());
                studentFullDTO.setSurname(personaEntity.getSurname());
                studentFullDTO.setCompanyEmail(personaEntity.getCompanyEmail());
                studentFullDTO.setPersonalEmail(personaEntity.getPersonalEmail());
                studentFullDTO.setCity(personaEntity.getCity());
                studentFullDTO.setActive(personaEntity.isActive());
                studentFullDTO.setCreatedDate(personaEntity.getCreatedDate());
                studentFullDTO.setImageUrl(personaEntity.getImageUrl());
                studentFullDTO.setTerminationDate(personaEntity.getTerminationDate());
            }

            return studentFullDTO;
        }).orElse(null);
    }
}
