package gestion.bibliotheque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Type_Pret")
public class TypePret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_pret")
    private Long id;

    @Column(name = "nom_type_pret", length = 50)
    private String nomTypePret;

    @Column(name = "duree_max")
    private Integer dureeMax;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTypePret() {
        return nomTypePret;
    }

    public void setNomTypePret(String nomTypePret) {
        this.nomTypePret = nomTypePret;
    }

    public Integer getDureeMax() {
        return dureeMax;
    }

    public void setDureeMax(Integer dureeMax) {
        this.dureeMax = dureeMax;
    }
}
