package gestion.bibliotheque.controller;

import gestion.bibliotheque.model.Adherent;
import gestion.bibliotheque.model.Exemplaire;
import gestion.bibliotheque.model.Reservation;
import gestion.bibliotheque.service.ReservationService;
import gestion.bibliotheque.service.StatutReservationService;
import gestion.bibliotheque.repository.AdherentRepository;
import gestion.bibliotheque.repository.ExemplaireRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private StatutReservationService statutReservationService;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    // 🔎 Afficher toutes les réservations pour un utilisateur
    @GetMapping("/user")
    public String afficherReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "user/reservation/liste"; // templates/user/reservation/liste.html
    }

    // ➕ Préparation du formulaire d'ajout de réservation
    @GetMapping("/ajouterUser")
    public String ajouterReservationDepuisExemplaire(@RequestParam("idExemplaire") Long idExemplaire,
                                                     Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Adherent adherent = adherentRepository.findById(userId).orElse(null);
        Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire).orElse(null);

        Reservation reservation = new Reservation();
        reservation.setAdherent(adherent);
        reservation.setExemplaire(exemplaire);
        reservation.setDateReservation(LocalDate.now().plusDays(1)); // par défaut demain

        model.addAttribute("reservation", reservation);
        return "user/reservation/ajout"; // templates/user/reservation/ajout.html
    }

    // ✅ Enregistrement de la réservation
    @PostMapping("/ajouterUser")
    public String enregistrerReservation(@ModelAttribute Reservation reservation) {
        reservation.setDateCreated(LocalDate.now());
        if (reservation.getDateReservation() == null) {
            reservation.setDateReservation(LocalDate.now().plusDays(1));
        }

        reservationService.saveWithDefaultStatut(reservation);
        return "redirect:/reservations/user";
    }
}
