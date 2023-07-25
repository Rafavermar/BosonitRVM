package com.block7crudvalidation.block7crudvalidation.services;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.ProfesorDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Output.ProfesorFullDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEstudiante;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Mapper.ProfesorMapper;
import com.block7crudvalidation.block7crudvalidation.Respository.ProfesorEstudianteRepository;
import com.block7crudvalidation.block7crudvalidation.Respository.ProfesorRepository;
import com.block7crudvalidation.block7crudvalidation.Respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final StudentRepository studentRepository;
    private final ProfesorEstudianteRepository profesorEstudianteRepository;
    private final ProfesorMapper profesorMapper;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository profesorRepository,
                               StudentRepository studentRepository,
                               ProfesorEstudianteRepository profesorEstudianteRepository,
                               ProfesorMapper profesorMapper) {
        this.profesorRepository = profesorRepository;
        this.studentRepository = studentRepository;
        this.profesorEstudianteRepository = profesorEstudianteRepository;
        this.profesorMapper = profesorMapper;
    }

    @Override
    public ProfesorEntity saveProfesor(ProfesorEntity profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public List<ProfesorEntity> getAllProfesores() {
        return profesorRepository.findAll();
    }

    @Override
    public ProfesorEntity getProfesorById(Integer id) {
        return profesorRepository.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void deleteProfesor(Integer idProfesor) {
        ProfesorEntity profesor = profesorRepository.findById(idProfesor)
                .orElseThrow(() -> new EntityNotFoundException(idProfesor));

        // Encuentra todos los registros de ProfesorEstudiante asociados a este profesor
        Set<ProfesorEstudiante> profesorEstudiantes = profesor.getProfesorEstudiantes();

        for (ProfesorEstudiante pe : profesorEstudiantes) {
            // Establece la referencia del profesor a null en los estudiantes asociados
            StudentEntity student = pe.getStudent();
            student.setProfesor(null);
            studentRepository.save(student);

            // Ahora puedes eliminar el registro ProfesorEstudiante
            profesorEstudianteRepository.delete(pe);
        }

        // Finalmente, puedes eliminar el profesor
        profesorRepository.delete(profesor);
    }






    @Override
    public ProfesorEntity updateProfesor(Integer id, ProfesorEntity profesor) {
        ProfesorEntity existingProfesor = profesorRepository.findById(id).orElse(null);
        if (existingProfesor != null) {
            existingProfesor.setPersona(profesor.getPersona());
            existingProfesor.setComments(profesor.getComments());
            existingProfesor.setBranch(profesor.getBranch());
            return profesorRepository.save(existingProfesor);
        }
        return null;
    }

    @Override
    public ProfesorDTO getProfesorDTOById(Integer id) {
        ProfesorEntity profesorEntity = profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return profesorMapper.toDTO(profesorEntity);
    }

    @Override
    public List<ProfesorDTO> getProfesoresDTOByName(String name) {
        List<ProfesorEntity> profesorEntities = profesorRepository.findByPersonaName(name);
        return profesorMapper.toDTOList(profesorEntities);
    }
    @Override
    public ProfesorFullDTO getProfesorFullDetailsById(Integer id) {
        ProfesorEntity profesorEntity = profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return profesorMapper.toFullDTO(profesorEntity);
    }

    @Override
    public List<ProfesorFullDTO> getProfesorFullDetailsByName(String name) {
        List<ProfesorEntity> profesorEntities = profesorRepository.findByPersonaName(name);
        return profesorMapper.toFullDTOList(profesorEntities);
    }

}
