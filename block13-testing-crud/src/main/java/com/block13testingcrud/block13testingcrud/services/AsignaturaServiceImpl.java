package com.block13testingcrud.block13testingcrud.services;

import com.block13testingcrud.block13testingcrud.dto.input.AsignaturaInputDTO;
import com.block13testingcrud.block13testingcrud.entities.AsignaturaEntity;
import com.block13testingcrud.block13testingcrud.entities.StudentEntity;
import com.block13testingcrud.block13testingcrud.exception.EntityNotFoundException;
import com.block13testingcrud.block13testingcrud.mapper.AsignaturaMapper;
import com.block13testingcrud.block13testingcrud.repository.AsignaturaRepository;
import com.block13testingcrud.block13testingcrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private AsignaturaMapper asignaturaMapper;

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfesorService profesorService;

    @Override
    public List<AsignaturaEntity> getAsignaturasByStudentId(Integer idStudent) {
        return asignaturaRepository.findAllByStudents_IdStudent(idStudent);
    }

    @Override
    public AsignaturaInputDTO createAsignatura(AsignaturaInputDTO asignaturaInputDTO) {
        // Ya no necesitamos obtener el estudiante, ya que no lo vinculamos durante la creación
        // StudentEntity studentEntity = studentService.getStudentById(asignaturaInputDTO.getIdStudent());

        // Solo pasamos el DTO al mapper
        AsignaturaEntity newAsignatura = asignaturaMapper.toEntity(asignaturaInputDTO);
        AsignaturaEntity savedAsignatura = asignaturaRepository.save(newAsignatura);
        return asignaturaMapper.toDTO(savedAsignatura);
    }


    @Override
    public ResponseEntity<?> deleteAsignatura(Integer idAsignatura) {
        Optional<AsignaturaEntity> asignatura = asignaturaRepository.findById(idAsignatura);

        if (asignatura.isPresent()) {
            // obtiene los alumnos asociados a esta asignatura
            List<StudentEntity> students = asignatura.get().getStudents();

            // borra la asignatura para cada estudiente
            for (StudentEntity student : students) {
                student.getAsignaturas().remove(asignatura.get());
                studentRepository.save(student);
            }

            // borra la asignatura
            asignaturaRepository.delete(asignatura.get());
            return new ResponseEntity<>("Asignatura eliminada correctamente", HttpStatus.OK);
        } else {
            throw new RuntimeException("Asignatura not found with id: " + idAsignatura);
        }
    }


    @Override
    public List<AsignaturaInputDTO> getAllAsignaturas() {
        List<AsignaturaEntity> asignaturaEntities = asignaturaRepository.findAll();
        return asignaturaMapper.toDTOList(asignaturaEntities);
    }

    @Override
    public List<StudentEntity> getStudentByAsignaturaId(Integer idAsignatura) {
        AsignaturaEntity asignatura = asignaturaRepository.findById(idAsignatura)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró una asignatura con el id: " + idAsignatura));
        return asignatura.getStudents();
    }



}
