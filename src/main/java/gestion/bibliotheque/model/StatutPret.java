package gestion.bibliotheque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Statut_Pret")
public class StatutPret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statut_pret")
    private Long id;

    @Column(name = "nom_statut", nullable = false, unique = true, length = 50)
    private String nomStatut;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomStatut() {
        return nomStatut;
    }

    public void setNomStatut(String nomStatut) {
        this.nomStatut = nomStatut;
    }
}
