package de.example.backend.controller;

import de.example.backend.entity.Tutor;
import de.example.backend.exceptions.TutorNotFoundException;
import de.example.backend.model.TutorDTO;
import de.example.backend.service.TutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutors")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping
    public List<TutorDTO> findAll() {
        return tutorService.findAll();
    }

    @PostMapping
    public ResponseEntity<Tutor> create(@RequestBody TutorDTO tutorDTO) {
        Tutor newTutor = tutorService.create(tutorDTO);
        return new ResponseEntity<>(newTutor, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Tutor> update(@RequestBody TutorDTO tutorDTO) {
        try {
            Tutor updatedTutor = tutorService.update(tutorDTO);
            return new ResponseEntity<>(updatedTutor, HttpStatus.OK);
        } catch (TutorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody TutorDTO tutorDTO) {
        try {
            tutorService.delete(tutorDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TutorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
