package gestion.bibliotheque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Statut_Reservation")
public class StatutReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_statut", nullable = false, unique = true, length = 50)
    private String nomStatut;

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomStatut() {
        return nomStatut;
    }

    public void setNomStatut(String nomStatut) {
        this.nomStatut = nomStatut;
    }
}
