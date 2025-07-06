package gestion.bibliotheque.service;

import gestion.bibliotheque.model.StatutReservation;
import gestion.bibliotheque.repository.StatutReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatutReservationService {

    @Autowired
    private StatutReservationRepository statutReservationRepository;

    public StatutReservation findByNom(String nom) {
        return statutReservationRepository
            .findAll()
            .stream()
            .filter(s -> s.getNomStatut().equalsIgnoreCase(nom))
            .findFirst()
            .orElse(null);
    }

    public List<StatutReservation> findAll() {
        return statutReservationRepository.findAll();
    }
}
