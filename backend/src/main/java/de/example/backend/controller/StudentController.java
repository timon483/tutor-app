package de.example.backend.controller;

import de.example.backend.entity.Student;
import de.example.backend.exceptions.StudentNotFoundException;
import de.example.backend.model.StudentDTO;
import de.example.backend.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> findAll() {
        return studentService.findAll();
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody StudentDTO studentDTO) {
        Student student = studentService.create(studentDTO);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody StudentDTO studentDTO) {
        try {
            Student updatedStudent = studentService.update(studentDTO);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody StudentDTO studentDTO) {
        try {
            studentService.delete(studentDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
