package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.DTO.PersonaDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.PersonaNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Exception.UnprocessableEntityException;
import com.block7crudvalidation.block7crudvalidation.Mappers.PersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PersonaServiceImpl implements PersonaService {

    private final List<PersonaEntity> personas = new ArrayList<>();
    private final PersonaMapper personaMapper;

    public PersonaServiceImpl(PersonaMapper personaMapper) {
        this.personaMapper = personaMapper;
    }

    @Override
    public PersonaDTO agregarPersona(PersonaDTO personaDTO) {
        if (personaDTO.getName() == null || personaDTO.getName().isEmpty() ||
                personaDTO.getCompany_email() == null || personaDTO.getCompany_email().isEmpty() ||
                personaDTO.getPersonal_email() == null || personaDTO.getPersonal_email().isEmpty() ||
                personaDTO.getCity() == null || personaDTO.getCity().isEmpty()) {
            throw new UnprocessableEntityException("Todos los campos (name, company_email, personal_email, city) deben estar presentes y no pueden estar vacíos.");
        }
        PersonaEntity personaEntity = personaMapper.convertToEntity(personaDTO);
        personas.add(personaEntity);
        return personaMapper.convertToDTO(personaEntity);
    }

    /**
     * @Override
     * public PersonOutputDto addPerson(PersonInputDto person) throws UnprocessableEntityException {
     * String mensajes = "";
     * if (person.getUsuario()==null) {mensajes += "Usuario no puede ser nulo\n";}
     * else if (person.getUsuario().length()>10) {mensajes += "Longitud de usuario no puede ser superior a 10 caracteres\n";}
     * else if (person.getUsuario().length()<6) {mensajes += "Longitud de usuario no puede ser inferior a 6 caracteres\n";}
     * if (person.getPassword() == null) {mensajes += "Contraseña no puede ser nulo\n";}
     * if (person.getName() == null) {mensajes += "Nombre no puede ser nulo\n";}
     * if (person.getCompanyEmail() == null) {mensajes += "Email de la compañia no puede ser nulo\n";}
     * if (person.getPersonalEmail() == null) {mensajes += "Email personal no puede ser nulo\n";}
     * if (person.getCity() == null) {mensajes += "Ciudad no puede ser nulo\n";}
     * if (person.getActive() == null) {mensajes += "Activo no puede ser nulo\n";}
     * if (person.getCreatedDate() == null) {mensajes += "Fecha de creacion no puede ser nulo\n";}
     * if(mensajes.isEmpty()){
     * return personRepository.save(new Person(person)).personToPersonOutputDto();
     * }else{
     * throw new UnprocessableEntityException(mensajes);
     * }
     * @param id
     * @return
     */

    @Override
    public PersonaDTO buscarPorId(int id) {
        PersonaEntity persona = personas.stream()
                .filter(p -> p.getIdPersona() == id)
                .findFirst()
                .orElseThrow(() -> new PersonaNotFoundException(id));
        return personaMapper.convertToDTO(persona);
    }

    @Override
    public PersonaDTO buscarPorUsuario(String usuario) {
        PersonaEntity persona = personas.stream()
                .filter(p -> p.getUsuario().equals(usuario))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con usuario: " + usuario));
        return personaMapper.convertToDTO(persona);
    }

    @Override
    public List<PersonaDTO> mostrarTodos() {
        return personas.stream()
                .map(personaMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
