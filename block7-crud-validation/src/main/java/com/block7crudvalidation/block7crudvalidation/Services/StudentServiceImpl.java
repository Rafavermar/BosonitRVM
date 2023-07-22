package com.block7crudvalidation.block7crudvalidation.Services;

import com.block7crudvalidation.block7crudvalidation.DTO.Input.StudentDTO;
import com.block7crudvalidation.block7crudvalidation.DTO.Output.EstudianteFullDTO;
import com.block7crudvalidation.block7crudvalidation.Entities.PersonaEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEntity;
import com.block7crudvalidation.block7crudvalidation.Entities.ProfesorEstudiante;
import com.block7crudvalidation.block7crudvalidation.Entities.StudentEntity;
import com.block7crudvalidation.block7crudvalidation.Exception.EntityNotFoundException;
import com.block7crudvalidation.block7crudvalidation.Mapper.StudentMapper;
import com.block7crudvalidation.block7crudvalidation.Respository.PersonaRepository;
import com.block7crudvalidation.block7crudvalidation.Respository.ProfesorEstudianteRepository;
import com.block7crudvalidation.block7crudvalidation.Respository.ProfesorRepository;
import com.block7crudvalidation.block7crudvalidation.Respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ProfesorRepository profesorRepository;
    private final PersonaRepository personaRepository;
    private final StudentMapper studentMapper;
    private final PersonaService personaService;
    private final ProfesorEstudianteRepository profesorEstudianteRepository;
    private final ProfesorService profesorService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ProfesorRepository profesorRepository,
                              PersonaRepository personaRepository, StudentMapper studentMapper,
                              PersonaService personaService,ProfesorEstudianteRepository profesorEstudianteRepository,
                              ProfesorService profesorService) {
        this.studentRepository = studentRepository;
        this.profesorRepository = profesorRepository;
        this.personaRepository = personaRepository;
        this.studentMapper = studentMapper;
        this.personaService = personaService;
        this.profesorEstudianteRepository = profesorEstudianteRepository;
        this.profesorService = profesorService;
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

    // Método para agregar estudiante con el profesor
    @Override
    public StudentDTO agregarEstudiante(StudentDTO studentDTO) {
        // Buscar la entidad PersonaEntity por su ID
        PersonaEntity personaEntity = personaService.buscarPorId(studentDTO.getIdPersona());
        if (personaEntity == null) {
            throw new EntityNotFoundException("Persona with id " + studentDTO.getIdPersona() + " not found");
        }

        // Buscar la entidad ProfesorEntity por su ID
        ProfesorEntity profesorEntity = null;
        if (studentDTO.getIdProfesor() != null) {
            profesorEntity = profesorRepository.findById(studentDTO.getIdProfesor())
                    .orElseThrow(() -> new EntityNotFoundException("Profesor with id " + studentDTO.getIdProfesor() + " not found"));
        }

        // Convertir el DTO a una entidad StudentEntity usando el mapper
        StudentEntity studentEntity = studentMapper.toEntity(studentDTO);

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
}
