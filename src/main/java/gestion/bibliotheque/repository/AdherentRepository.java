package gestion.bibliotheque.repository;


import  gestion.bibliotheque.model.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Long> {
    // Exemples de méthodes personnalisées si besoin :
    // List<Adherent> findByNomContainingIgnoreCase(String nom);

    @Query("SELECT a FROM Adherent a JOIN FETCH a.typeAdherent WHERE a.id = :id")
    Optional<Adherent> findAdherentWithTypeById(@Param("id") Long id);

      // 🔍 Trouver un adhérent par son nom
    Adherent findByNom(String nom);

    // (optionnel) Trouver par nom et mot de passe
    Adherent findByNomAndPassword(String nom, String password);
    Adherent findById(long id) ; 

}