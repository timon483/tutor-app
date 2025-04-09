package de.example.backend.service;

import de.example.backend.entity.Subject;
import de.example.backend.model.SubjectDTO;
import de.example.backend.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject createSubject(SubjectDTO subject) {
        if (subjectRepository.findByName(subject.getName()) == null) {
            Subject newSubject = new Subject();
            newSubject.setName(subject.getName());
            return subjectRepository.save(newSubject);
        } else {
            return subjectRepository.findByName(subject.getName());
        }
    }

    public List<String> findAll() {
        return subjectRepository.findAll().stream().map(Subject::getName).collect(Collectors.toList());
    }



}
