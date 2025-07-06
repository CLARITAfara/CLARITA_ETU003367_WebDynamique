package gestion.bibliotheque.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titre;

    @Column(length = 100)
    private String auteur;

    @Column(length = 13)
    private String isbn;

    @Column(name = "date_publication")
    private LocalDate datePublication;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "nbr_exmp")
    private Integer nbrExmp = 1;

    @Column(name = "age_restriction")
    private Integer ageRestriction;

    // Nouveaux champs ajoutés
    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @PrePersist
    protected void onCreate() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters et Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }

    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }

    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public LocalDate getDatePublication() { return datePublication; }

    public void setDatePublication(LocalDate datePublication) { this.datePublication = datePublication; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public Integer getAgeRestriction() { return ageRestriction; }

    public void setAgeRestriction(Integer ageRestriction) { this.ageRestriction = ageRestriction; }

    public Integer getNbrExmp() { return nbrExmp; }

    public void setNbrExmp(Integer nbrExmp) { this.nbrExmp = nbrExmp; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
