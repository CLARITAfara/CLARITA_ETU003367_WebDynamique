package gestion.bibliotheque.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Prolongement_Pret")
public class ProlongementPret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    @Column(name = "date_prolongement", nullable = false)
    private LocalDate dateProlongement;

    @Column(name = "date_retour_prevue", nullable = false)
    private LocalDate dateRetourPrevue;

    @ManyToOne
    @JoinColumn(name = "statut", nullable = false)
    private StatutPret statut;

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public LocalDate getDateProlongement() {
        return dateProlongement;
    }

    public void setDateProlongement(LocalDate dateProlongement) {
        this.dateProlongement = dateProlongement;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public StatutPret getStatut() {
        return statut;
    }

    public void setStatut(StatutPret statut) {
        this.statut = statut;
    }
}
