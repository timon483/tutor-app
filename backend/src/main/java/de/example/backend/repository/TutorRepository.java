package de.example.backend.repository;

import de.example.backend.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
