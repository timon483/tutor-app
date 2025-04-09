package de.example.backend.repository;

import de.example.backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String subject);

    List<Subject> findByNameIn(Collection<String> names);
}
