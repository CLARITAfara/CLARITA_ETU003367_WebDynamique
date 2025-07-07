package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutReservationRepository extends JpaRepository<StatutReservation, Integer> {
}
