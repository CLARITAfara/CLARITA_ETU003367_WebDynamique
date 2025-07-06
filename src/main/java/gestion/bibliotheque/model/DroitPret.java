package gestion.bibliotheque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Droit_Pret")
public class DroitPret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_type_adherent", nullable = false)
    private TypeAdherent typeAdherent;

    @ManyToOne
    @JoinColumn(name = "id_type_utilisation", nullable = false)
    private TypeUtilisation typeUtilisation;

    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    @Column(nullable = false)
    private Boolean autorise = false;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeAdherent getTypeAdherent() {
        return typeAdherent;
    }

    public void setTypeAdherent(TypeAdherent typeAdherent) {
        this.typeAdherent = typeAdherent;
    }

    public TypeUtilisation getTypeUtilisation() {
        return typeUtilisation;
    }

    public void setTypeUtilisation(TypeUtilisation typeUtilisation) {
        this.typeUtilisation = typeUtilisation;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Boolean getAutorise() {
        return autorise;
    }

    public void setAutorise(Boolean autorise) {
        this.autorise = autorise;
    }
}
