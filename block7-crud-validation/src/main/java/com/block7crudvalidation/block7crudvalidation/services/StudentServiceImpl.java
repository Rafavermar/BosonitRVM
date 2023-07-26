package com.block7crudvalidation.block7crudvalidation.services;

import com.block7crudvalidation.block7crudvalidation.dto.input.StudentInputDto;
import com.block7crudvalidation.block7crudvalidation.dto.output.EstudianteFullOutDto;
import com.block7crudvalidation.block7crudvalidation.dto.output.EstudianteSimpleOutDto;
import com.block7crudvalidation.block7crudvalidation.entities.*;
import com.block7crudvalidation.block7crudvalidation.exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.mapper.StudentMapper;
import com.block7crudvalidation.block7crudvalidation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ProfesorRepository profesorRepository;
    private final PersonaRepository personaRepository;
    private final StudentMapper studentMapper;
    private final PersonaService personaService;
    private final ProfesorEstudianteRepository profesorEstudianteRepository;
    private final ProfesorService profesorService;

    private final AsignaturaRepository asignaturaRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ProfesorRepository profesorRepository,
                              PersonaRepository personaRepository, StudentMapper studentMapper,
                              PersonaService personaService, ProfesorEstudianteRepository profesorEstudianteRepository,
                              ProfesorService profesorService, AsignaturaRepository asignaturaRepository) {
        this.studentRepository = studentRepository;
        this.profesorRepository = profesorRepository;
        this.personaRepository = personaRepository;
        this.studentMapper = studentMapper;
        this.personaService = personaService;
        this.profesorEstudianteRepository = profesorEstudianteRepository;
        this.profesorService = profesorService;
        this.asignaturaRepository = asignaturaRepository;
    }

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

    @Transactional
    @Override
    public void deleteStudent(Integer idStudent) throws EntityNotFoundException {
        StudentEntity studentEntity = studentRepository.findByIdStudent(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("El estudiante con ID: " + idStudent + " no fue encontrado."));

        // Elimina las relaciones con ProfesorEntity en ProfesorEstudiante
        List<ProfesorEstudiante> profesorEstudiantes = profesorEstudianteRepository.findByStudent(studentEntity);
        if (!profesorEstudiantes.isEmpty()) {
            profesorEstudianteRepository.deleteByStudent(studentEntity);
        }

        // Elimina las relaciones con AsignaturaEntity
        List<AsignaturaEntity> asignaturas = studentEntity.getAsignaturas();
        for(AsignaturaEntity asignatura : asignaturas) {
            asignatura.getStudents().remove(studentEntity); // Desvincula el estudiante de la asignatura
        }
        studentEntity.getAsignaturas().clear(); // Limpia la lista de asignaturas del estudiante
        studentRepository.save(studentEntity); // Guarda el estudiante desvinculado

        // Elimina el estudiante
        studentRepository.delete(studentEntity);
    }


    @Override
    public EstudianteFullOutDto getStudentFullDetails(Integer id) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(id);
        return studentOptional.map(studentEntity -> {
            EstudianteFullOutDto studentFullDTO = new EstudianteFullOutDto();
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

    // Método para agregar estudiante con el profesor
    @Override
    public StudentInputDto agregarEstudiante(StudentInputDto studentInputDto) {
        // Buscar la entidad PersonaEntity por su ID
        PersonaEntity personaEntity = personaService.buscarPorId(studentInputDto.getIdPersona());
        if (personaEntity == null) {
            throw new EntityNotFoundException("Persona with id " + studentInputDto.getIdPersona() + " not found");
        }

        // Buscar la entidad ProfesorEntity por su ID
        ProfesorEntity profesorEntity = null;
        if (studentInputDto.getIdProfesor() != null) {
            profesorEntity = profesorRepository.findById(studentInputDto.getIdProfesor())
                    .orElseThrow(() -> new EntityNotFoundException("Profesor with id " + studentInputDto.getIdProfesor() + " not found"));
        }

        // Convertir el DTO a una entidad StudentEntity usando el mapper
        StudentEntity studentEntity = studentMapper.toEntity(studentInputDto);

        // Establecer la entidad PersonaEntity en la nueva entidad StudentEntity
        studentEntity.setPersona(personaEntity);

        // Si se asignó un profesor, actualizar la relación en ambas direcciones
        if (profesorEntity != null) {
            // Asignar el profesor al estudiante
            studentEntity.setProfesor(profesorEntity);
            profesorEntity.getStudents().add(studentEntity); // Agregar el estudiante al profesor
        }

        // Guardar el estudiante en la base de datos
        StudentEntity nuevoEstudiante = saveStudent(studentEntity);

        // Si se asignó un profesor, actualizar la relación en ambas direcciones
        if (profesorEntity != null) {
            // Asignar el profesor al estudiante
            studentEntity.setProfesor(profesorEntity);

            // Agregar la relación en la tabla intermedia
            ProfesorEstudiante profesorEstudiante = new ProfesorEstudiante();
            profesorEstudiante.setProfesor(profesorEntity);
            profesorEstudiante.setStudent(nuevoEstudiante);
            profesorEstudianteRepository.save(profesorEstudiante);

            // Agregar el estudiante a la lista de estudiantes del profesor
            profesorEntity.getProfesorEstudiantes().add(profesorEstudiante);
            profesorService.saveProfesor(profesorEntity);
        }

        // Convertir el estudiante guardado a DTO y devolverlo en la respuesta
        return studentMapper.toDTO(nuevoEstudiante);

    }

    @Override
    public StudentInputDto getStudentDTOById(Integer id) {
        StudentEntity studentEntity = getStudentById(id);
        if (studentEntity != null) {
            return studentMapper.toDTO(studentEntity);
        }
        return null;
    }
    @Override
    public List<StudentInputDto> getStudentsDTOByName(String name) {
        // Find students by name in the database
        List<StudentEntity> studentEntities = studentRepository.findByPersonaName(name);

        // Convert the entities to DTOs using the mapper
        return studentEntities.stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EstudianteFullOutDto> getStudentFullDetailsByName(String name) {
        List<StudentEntity> studentEntities = studentRepository.findByPersona_Name(name);
        return studentEntities.stream()
                .map(studentEntity -> getStudentFullDetails(studentEntity.getIdStudent()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void asignarAsignaturasStudent(Integer idStudent, List<Integer> idsAsignaturas) {
        StudentEntity student = studentRepository.findById(idStudent).orElseThrow(() -> new EntityNotFoundException(idStudent.toString()));
        List<AsignaturaEntity> asignaturas = asignaturaRepository.findAllById(idsAsignaturas);
        student.getAsignaturas().addAll(asignaturas);
        asignaturas.forEach(a -> a.getStudents().add(student));
        studentRepository.save(student);
    }

    @Transactional
    public void desasignarAsignaturasStudent(Integer idStudent, List<Integer> idsAsignaturas) {
        StudentEntity student = studentRepository.findById(idStudent).orElseThrow(() -> new EntityNotFoundException(idStudent.toString()));
        List<AsignaturaEntity> asignaturas = asignaturaRepository.findAllById(idsAsignaturas);
        student.getAsignaturas().removeAll(asignaturas);
        asignaturas.forEach(a -> a.getStudents().remove(student));
        studentRepository.save(student);
    }
    @Override
    public List<EstudianteFullOutDto> getStudentFullDetails() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities.stream()
                .map(studentEntity -> getStudentFullDetails(studentEntity.getIdStudent()))
                .collect(Collectors.toList());
    }

    public List<EstudianteSimpleOutDto> getAllStudentsSimpleDetails() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities.stream()
                .map(this::convertToSimpleDto)
                .collect(Collectors.toList());
    }

    private EstudianteSimpleOutDto convertToSimpleDto(StudentEntity entity) {
        EstudianteSimpleOutDto dto = new EstudianteSimpleOutDto();
        dto.setIdStudent(entity.getIdStudent());
        dto.setNumHoursWeek(entity.getNumHoursWeek());
        dto.setComments(entity.getComments());
        return dto;
    }


}
