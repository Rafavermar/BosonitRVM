package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.DTO.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Exception.UnprocessableEntityException;
import com.block7crudvalidation.block7crudvalidation.Respository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public PersonaDTO agregarPersona(PersonaDTO personaDTO) {
        // Validar los campos requeridos y lanzar UnprocessableEntityException en caso de que no cumplan los requisitos
        if (personaDTO.getUsuario() == null || personaDTO.getName() == null || personaDTO.getCity() == null) {
            throw new UnprocessableEntityException("Todos los campos (usuario, name, city) deben estar presentes y no pueden estar vacíos.");
        }

        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setUsuario(personaDTO.getUsuario());
        personaEntity.setPassword(personaDTO.getPassword());
        personaEntity.setName(personaDTO.getName());
        personaEntity.setSurname(personaDTO.getSurname());
        personaEntity.setCompanyEmail(personaDTO.getCompany_email());
        personaEntity.setPersonalEmail(personaDTO.getPersonal_email());
        personaEntity.setCity(personaDTO.getCity());
        personaEntity.setActive(personaDTO.isActive());
        personaEntity.setCreatedDate(new Date());
        personaEntity.setImageUrl(personaDTO.getImagen_url());
        personaEntity.setTerminationDate(personaDTO.getTermination_date());

        personaRepository.save(personaEntity);

        return convertToDTO(personaEntity);
    }

    @Override
    public PersonaDTO buscarPorId(int id) {
        PersonaEntity personaEntity = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        return convertToDTO(personaEntity);
    }

    @Override
    public PersonaDTO buscarPorUsuario(String usuario) {
        PersonaEntity personaEntity = personaRepository.findByUsuario(usuario)
                .orElseThrow(() -> new EntityNotFoundException(usuario));

        return convertToDTO(personaEntity);
    }

    @Override
    public List<PersonaDTO> mostrarTodos() {
        List<PersonaEntity> personasEntity = personaRepository.findAll();
        List<PersonaDTO> personasDTO = new ArrayList<>();

        for (PersonaEntity personaEntity : personasEntity) {
            personasDTO.add(convertToDTO(personaEntity));
        }

        return personasDTO;
    }

    private PersonaDTO convertToDTO(PersonaEntity personaEntity) {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(personaEntity.getIdPersona());
        personaDTO.setUsuario(personaEntity.getUsuario());
        personaDTO.setPassword(personaEntity.getPassword());
        personaDTO.setName(personaEntity.getName());
        personaDTO.setSurname(personaEntity.getSurname());
        personaDTO.setCompany_email(personaEntity.getCompanyEmail());
        personaDTO.setPersonal_email(personaEntity.getPersonalEmail());
        personaDTO.setCity(personaEntity.getCity());
        personaDTO.setActive(personaEntity.isActive());
        personaDTO.setCreated_date(personaEntity.getCreatedDate());
        personaDTO.setImagen_url(personaEntity.getImageUrl());
        personaDTO.setTermination_date(personaEntity.getTerminationDate());

        return personaDTO;
    }
}
