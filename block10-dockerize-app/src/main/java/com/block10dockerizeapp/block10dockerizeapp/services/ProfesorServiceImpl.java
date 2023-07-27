package com.block10dockerizeapp.block10dockerizeapp.services;


import com.block10dockerizeapp.block10dockerizeapp.dto.input.ProfesorInputDto;
import com.block10dockerizeapp.block10dockerizeapp.dto.output.ProfesorFullOutputDto;
import com.block10dockerizeapp.block10dockerizeapp.entities.PersonaEntity;
import com.block10dockerizeapp.block10dockerizeapp.entities.ProfesorEntity;
import com.block10dockerizeapp.block10dockerizeapp.entities.ProfesorEstudiante;
import com.block10dockerizeapp.block10dockerizeapp.entities.StudentEntity;
import com.block10dockerizeapp.block10dockerizeapp.exception.EntityNotFoundException;
import com.block10dockerizeapp.block10dockerizeapp.mapper.ProfesorMapper;
import com.block10dockerizeapp.block10dockerizeapp.repository.ProfesorEstudianteRepository;
import com.block10dockerizeapp.block10dockerizeapp.repository.ProfesorRepository;
import com.block10dockerizeapp.block10dockerizeapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final StudentRepository studentRepository;
    private final ProfesorEstudianteRepository profesorEstudianteRepository;
    private final ProfesorMapper profesorMapper;
    private final PersonaService personaService;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository profesorRepository,
                               StudentRepository studentRepository,
                               ProfesorEstudianteRepository profesorEstudianteRepository,
                               ProfesorMapper profesorMapper, PersonaService personaService) {
        this.profesorRepository = profesorRepository;
        this.studentRepository = studentRepository;
        this.profesorEstudianteRepository = profesorEstudianteRepository;
        this.profesorMapper = profesorMapper;
        this.personaService = personaService;
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
    public ProfesorInputDto getProfesorDTOById(Integer id) {
        ProfesorEntity profesorEntity = profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return profesorMapper.toDTO(profesorEntity);
    }

    @Override
    public List<ProfesorInputDto> getProfesoresDTOByName(String name) {
        List<ProfesorEntity> profesorEntities = profesorRepository.findByPersonaName(name);
        return profesorMapper.toDTOList(profesorEntities);
    }
    @Override
    public ProfesorFullOutputDto getProfesorFullDetailsById(Integer id) {
        ProfesorEntity profesorEntity = profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return profesorMapper.toFullDTO(profesorEntity);
    }

    @Override
    public List<ProfesorFullOutputDto> getProfesorFullDetailsByName(String name) {
        List<ProfesorEntity> profesorEntities = profesorRepository.findByPersonaName(name);
        return profesorMapper.toFullDTOList(profesorEntities);
    }
    @Override
    public ProfesorInputDto createProfesor(ProfesorInputDto profesorInputDto) {
        // Mueve la lógica desde el controlador aquí.
        // Obtener el ID de persona desde el DTO del profesor
        Integer idPersona = profesorInputDto.getIdPersona();

        // Buscar la entidad PersonaEntity por su ID desde el servicio
        PersonaEntity personaEntity = personaService.buscarPorId(idPersona);

        // Convertir el DTO a una entidad ProfesorEntity usando el mapper
        ProfesorEntity profesorEntity = profesorMapper.toEntity(profesorInputDto);

        // Establecer la entidad PersonaEntity en la nueva entidad ProfesorEntity
        profesorEntity.setPersona(personaEntity);

        // Guardar el profesor en la base de datos
        ProfesorEntity nuevoProfesor = saveProfesor(profesorEntity);

        // Convertir el profesor guardado a DTO
        return profesorMapper.toDTO(nuevoProfesor);
    }

    @Override
    public ProfesorInputDto updateProfesor(Integer id, ProfesorInputDto profesorInputDto) {
        Integer idPersona = profesorInputDto.getIdPersona();
        PersonaEntity personaEntity = personaService.buscarPorId(idPersona);
        ProfesorEntity profesorEntity = profesorMapper.toEntity(profesorInputDto);
        profesorEntity.setPersona(personaEntity);
        ProfesorEntity updatedProfesor = updateProfesorEntity(id, profesorEntity);
        return profesorMapper.toDTO(updatedProfesor);
    }
    @Override
    public ProfesorEntity updateProfesorEntity(Integer id, ProfesorEntity profesorEntity) {
        ProfesorEntity existingProfesor = profesorRepository.findById(id).orElse(null);
        if (existingProfesor != null) {
            existingProfesor.setPersona(profesorEntity.getPersona());
            existingProfesor.setComments(profesorEntity.getComments());
            existingProfesor.setBranch(profesorEntity.getBranch());
            return profesorRepository.save(existingProfesor);
        }
        return null;
    }

}
