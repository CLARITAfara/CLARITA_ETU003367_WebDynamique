package gestion.bibliotheque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Type_Utilisation")
public class TypeUtilisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_type_utilisation", nullable = false, unique = true, length = 50)
    private String nomTypeUtilisation;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTypeUtilisation() {
        return nomTypeUtilisation;
    }

    public void setNomTypeUtilisation(String nomTypeUtilisation) {
        this.nomTypeUtilisation = nomTypeUtilisation;
    }
}
