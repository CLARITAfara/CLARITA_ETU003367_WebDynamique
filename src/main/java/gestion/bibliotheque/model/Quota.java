package gestion.bibliotheque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Quota")
public class Quota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_adherent", referencedColumnName = "id", nullable = false)
    private Adherent adherent;

    @Column(name = "curr_pret", nullable = false)
    private int currPret = 0;

    @Column(name = "curr_prolongement", nullable = false)
    private int currProlongement = 0;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public int getCurrPret() {
        return currPret;
    }

    public void setCurrPret(int currPret) {
        this.currPret = currPret;
    }

    public int getCurrProlongement() {
        return currProlongement;
    }

    public void setCurrProlongement(int currProlongement) {
        this.currProlongement = currProlongement;
    }
}