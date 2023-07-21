package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Respository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
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

    @Override
    public void deleteProfesor(Integer id) {
        profesorRepository.deleteById(id);
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
}
