package gestion.bibliotheque.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
 
import java.time.Period;
@Entity
@Table(name = "Adherent")
public class Adherent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "prenom", length = 100)
    private String prenom;

    @Column(name = "password", nullable = false, length = 200)
    private String password; // ✅ CHAMP MANQUANT

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private TypeAdherent typeAdherent;

    @Column(name = "est_abonne")
    private Boolean estAbonne = false;

    @Column(name = "est_penalise")
    private Boolean estPenalise = false;

    // --- Getters et Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getPassword() { return password; }  // ✅ Getter
    public void setPassword(String password) { this.password = password; } // ✅ Setter

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public LocalDate getDateInscription() { return dateInscription; }
    public void setDateInscription(LocalDate dateInscription) { this.dateInscription = dateInscription; }

    public TypeAdherent getTypeAdherent() { return typeAdherent; }
    public void setTypeAdherent(TypeAdherent typeAdherent) { this.typeAdherent = typeAdherent; }

    public Boolean getEstAbonne() { return estAbonne; }
    public void setEstAbonne(Boolean estAbonne) { this.estAbonne = estAbonne; }

    public Boolean getEstPenalise() { return estPenalise; }
    public void setEstPenalise(Boolean estPenalise) { this.estPenalise = estPenalise; }

    public int getAge() {
        LocalDate dateN = this.getDateNaissance()  ; 
        System.out.println("Date de naissance : " + dateN );
        if (dateN == null){
            return 0;   
        }  // ou -1 pour indiquer une date inconnue
        return Period.between(dateN , LocalDate.now()).getYears();
    }
}
