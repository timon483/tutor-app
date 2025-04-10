package de.example.backend.service;

import de.example.backend.entity.Subject;
import de.example.backend.entity.Tutor;
import de.example.backend.exceptions.TutorNotFoundException;
import de.example.backend.model.TutorDTO;
import de.example.backend.repository.SubjectRepository;
import de.example.backend.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;
    private final SubjectRepository subjectRepository;

    public TutorService(TutorRepository tutorRepository , SubjectRepository subjectRepository) {
        this.tutorRepository = tutorRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<TutorDTO> findAll() {
        List<Tutor> tutor = tutorRepository.findAll();
        return tutor.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Tutor create(TutorDTO tutorDTO) {
        return tutorRepository.save(mapToEntity(tutorDTO));
    }

    public Tutor update(TutorDTO tutorDTO) throws TutorNotFoundException {
        Optional<Tutor> tutorToUpdate = tutorRepository.findById(tutorDTO.getId());
        if (tutorToUpdate.isPresent()) {
            Tutor tutor = tutorToUpdate.get();
            Tutor updatedTutor = mapToEntity(tutorDTO);
            updatedTutor.setId(tutor.getId());
            tutorRepository.save(updatedTutor);
            return updatedTutor;
        } else {
            throw new TutorNotFoundException();
        }
    }

    public Tutor delete(TutorDTO tutorDTO) throws TutorNotFoundException {
        Optional<Tutor> tutorToDelete = tutorRepository.findById(tutorDTO.getId());
        if (tutorToDelete.isPresent()) {
            tutorRepository.delete(tutorToDelete.get());
            return tutorToDelete.get();
        } else {
            throw new TutorNotFoundException();
        }
    }

    public TutorDTO mapToDTO(Tutor tutor) {
        String price = formatPrice(tutor.getPricePerHour());
        List<String> subjects = tutor.getSubjectList().stream()
                .map(Subject::getName)
                .toList();

        return new TutorDTO(tutor.getId(), tutor.getFirstName(), tutor.getLastName(), tutor.getEmail(),
                tutor.getCity(), tutor.getPlz(), subjects, price);
    }

    public Tutor mapToEntity(TutorDTO tutorDTO) {
        Tutor tutor = new Tutor();
        tutor.setFirstName(tutorDTO.getFirstName());
        tutor.setLastName(tutorDTO.getLastName());
        tutor.setEmail(tutorDTO.getEmail());
        tutor.setCity(tutorDTO.getCity());
        tutor.setPlz(tutorDTO.getPlz());
        List<Subject> subjectList = subjectRepository.findByNameIn(tutorDTO.getSubjects());
        tutor.setSubjectList(subjectList);
        tutor.setPricePerHour(new BigDecimal(tutorDTO.getPrice()));
        return tutor;
    }


    public String formatPrice(BigDecimal price) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        currencyFormat.setCurrency(Currency.getInstance("EUR"));
        return currencyFormat.format(price);
    }
}
