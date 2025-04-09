package de.example.backend.service;

import de.example.backend.entity.Subject;
import de.example.backend.entity.Tutor;
import de.example.backend.model.TutorDTO;
import de.example.backend.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<TutorDTO> findAll() {
        List<Tutor> tutor = tutorRepository.findAll();
        return tutor.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public TutorDTO mapToDTO(Tutor tutor) {
        String price = formatPrice(tutor.getPricePerHour());
        List<String> subjects = tutor.getSubjectList().stream()
                .map(Subject::getName)
                .toList();

        return new TutorDTO(tutor.getId(), tutor.getFirstName(), tutor.getLastName(), tutor.getEmail(),
                tutor.getCity(), tutor.getPlz(), subjects, price);
    }


    public String formatPrice(BigDecimal price) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        currencyFormat.setCurrency(Currency.getInstance("EUR"));
        return currencyFormat.format(price);
    }
}
