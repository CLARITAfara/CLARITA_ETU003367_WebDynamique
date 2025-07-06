package gestion.bibliotheque.model;
 
import jakarta.persistence.*;

@Entity
@Table(name = "Statut_Paiement")
public class StatutPaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_statut", nullable = false, unique = true)
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
