package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
