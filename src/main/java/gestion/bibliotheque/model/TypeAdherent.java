package gestion.bibliotheque.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Type_Adherent")
public class TypeAdherent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_type", nullable = false, length = 50)
    private String nomType;

    @Column(name = "quota_max_pret", nullable = false)
    private int quotaMaxPret;

    @Column(name = "duree_max_pret", nullable = false)
    private int dureeMaxPret;

    @Column(name = "quota_max_prolongement", nullable = false)
    private int quotaMaxProlongement;

    @Column(name = "duree_max_prolongement", nullable = false)
    private int dureeMaxProlongement;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "typeAdherent")
    private List<Adherent> adherents;

    // === Constructeurs ===

    public TypeAdherent() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public TypeAdherent(String nomType, int quotaMaxPret, int dureeMaxPret,
                        int quotaMaxProlongement, int dureeMaxProlongement) {
        this.nomType = nomType;
        this.quotaMaxPret = quotaMaxPret;
        this.dureeMaxPret = dureeMaxPret;
        this.quotaMaxProlongement = quotaMaxProlongement;
        this.dureeMaxProlongement = dureeMaxProlongement;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // === Getters et Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public int getQuotaMaxPret() {
        return quotaMaxPret;
    }

    public void setQuotaMaxPret(int quotaMaxPret) {
        this.quotaMaxPret = quotaMaxPret;
    }

    public int getDureeMaxPret() {
        return dureeMaxPret;
    }

    public void setDureeMaxPret(int dureeMaxPret) {
        this.dureeMaxPret = dureeMaxPret;
    }

    public int getQuotaMaxProlongement() {
        return quotaMaxProlongement;
    }

    public void setQuotaMaxProlongement(int quotaMaxProlongement) {
        this.quotaMaxProlongement = quotaMaxProlongement;
    }

    public int getDureeMaxProlongement() {
        return dureeMaxProlongement;
    }

    public void setDureeMaxProlongement(int dureeMaxProlongement) {
        this.dureeMaxProlongement = dureeMaxProlongement;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Adherent> getAdherents() {
        return adherents;
    }

    public void setAdherents(List<Adherent> adherents) {
        this.adherents = adherents;
    }
}
