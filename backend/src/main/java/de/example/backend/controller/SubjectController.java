package de.example.backend.controller;

import de.example.backend.entity.Subject;
import de.example.backend.model.SubjectDTO;
import de.example.backend.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<String> findAll() {
        return subjectService.findAll();
    }

    @PostMapping
    public ResponseEntity<Subject> create(@RequestBody SubjectDTO subject) {
        Subject newSubject = subjectService.createSubject(subject);
        return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
    }
}
