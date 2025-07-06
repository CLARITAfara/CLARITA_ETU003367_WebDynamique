package gestion.bibliotheque.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Historique_Statut_Pret")
public class HistoriqueStatutPret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historique")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    @ManyToOne
    @JoinColumn(name = "id_statut_pret", nullable = false)
    private StatutPret statutPret;

    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin admin;

    @Column(name = "date_changement")
    private LocalDateTime dateChangement;

    @PrePersist
    protected void onCreate() {
        this.dateChangement = LocalDateTime.now();
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public StatutPret getStatutPret() {
        return statutPret;
    }

    public void setStatutPret(StatutPret statutPret) {
        this.statutPret = statutPret;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public LocalDateTime getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(LocalDateTime dateChangement) {
        this.dateChangement = dateChangement;
    }
}
