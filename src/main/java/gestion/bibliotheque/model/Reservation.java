package gestion.bibliotheque.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_adherent")
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "date_reservation", nullable = false)
    private LocalDate dateReservation;

    @Column(name = "is_validate")
    private Boolean isValidate = false;

    @ManyToOne
    @JoinColumn(name = "statut_id")
    private StatutReservation statut;

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Boolean getIsValidate() {
        return isValidate;
    }

    public void setIsValidate(Boolean isValidate) {
        this.isValidate = isValidate;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }
    
    
}