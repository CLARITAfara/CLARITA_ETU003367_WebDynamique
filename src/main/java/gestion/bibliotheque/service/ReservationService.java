package gestion.bibliotheque.service;

import gestion.bibliotheque.model.Reservation;
import gestion.bibliotheque.model.StatutReservation;
import gestion.bibliotheque.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private StatutReservationService statutReservationService;

    public Reservation saveWithDefaultStatut(Reservation reservation) {
        if (reservation.getStatut() == null) {
            StatutReservation defaultStatut = statutReservationService.findByNom("en_attente");
            reservation.setStatut(defaultStatut);
        }
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
