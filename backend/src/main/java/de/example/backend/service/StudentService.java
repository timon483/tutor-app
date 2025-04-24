package de.example.backend.service;

import de.example.backend.entity.Student;
import de.example.backend.entity.Subject;
import de.example.backend.exceptions.StudentNotFoundException;
import de.example.backend.model.StudentDTO;
import de.example.backend.repository.StudentRepository;
import de.example.backend.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public StudentDTO mapToDTO(Student student) {
        Set<String> subjects = student.getSubjects().stream()
                .map(Subject::getName)
                .collect(Collectors.toSet());

        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getCity(),
                student.getPlz(),
                subjects,
                student.getDateOfBirth()
        );
    }

    public Student mapToEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setCity(studentDTO.getCity());
        student.setPlz(studentDTO.getPlz());
        Set<Subject> subjects = subjectRepository.findByNameIn(studentDTO.getSubjects());
        student.setSubjects(subjects);
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        return student;
    }

    public List<StudentDTO> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Student create(StudentDTO studentDTO) {
        Student student = mapToEntity(studentDTO);
        return studentRepository.save(student);
    }
    public Student update(StudentDTO studentDTO) throws StudentNotFoundException {
        Optional<Student> studentToUpdate = studentRepository.findById(studentDTO.getId());
        if (studentToUpdate.isPresent()) {
            Student student = studentToUpdate.get();
            Student updatedStudent = mapToEntity(studentDTO);
            updatedStudent.setId(student.getId());
            studentRepository.save(updatedStudent);
            return updatedStudent;
        } else {
            throw new StudentNotFoundException();
        }
    }

    public void delete(StudentDTO studentDTO) throws StudentNotFoundException {
        Optional<Student> studentToDelete = studentRepository.findById(studentDTO.getId());
        if (studentToDelete.isPresent()) {
            studentRepository.delete(studentToDelete.get());
        } else {
            throw new StudentNotFoundException();
        }
    }

}
