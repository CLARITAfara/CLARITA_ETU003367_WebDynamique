package gestion.bibliotheque.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Exemplaire")
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exemplaire")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    @Column(name = "code_exemplaire", nullable = false, unique = true, length = 50)
    private String codeExemplaire;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_status") // Ceci doit correspondre à la colonne SQL
    private StatutPret statutPret;


    @PrePersist
    protected void onCreate() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Exemplaire() {
        // Constructeur vide requis par JPA
    }

    public Exemplaire(Livre livre, String codeExemplaire) {
        this.livre = livre;
        this.codeExemplaire = codeExemplaire;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public String getCodeExemplaire() {
        return codeExemplaire;
    }

    public void setCodeExemplaire(String codeExemplaire) {
        this.codeExemplaire = codeExemplaire;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

       public StatutPret getStatutPret() {
        return statutPret;
    }

    public void setStatutPret(StatutPret statutPret) {
        this.statutPret = statutPret;
    }
}
