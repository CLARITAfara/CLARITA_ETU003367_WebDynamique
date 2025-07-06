package gestion.bibliotheque.model;

 
import jakarta.persistence.*;

@Entity
@Table(name = "Type_Payement")
public class TypePayement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "montant_payement", nullable = false)
    private int montantPayement;

    @Column(name = "durre_validite")
    private int durreValidite;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getMontantPayement() {
        return montantPayement;
    }

    public void setMontantPayement(int montantPayement) {
        this.montantPayement = montantPayement;
    }

    public int getDurreValidite() {
        return durreValidite;
    }

    public void setDurreValidite(int durreValidite) {
        this.durreValidite = durreValidite;
    }
}
