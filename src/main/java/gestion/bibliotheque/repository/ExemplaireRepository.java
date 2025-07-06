package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.Exemplaire;
import gestion.bibliotheque.model.*;
import gestion.bibliotheque.service.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
    Optional<Exemplaire> findByCodeExemplaire(String codeExemplaire);
    List<Exemplaire> findByLivreId(Long livreId);

    @Query("SELECT e FROM Exemplaire e JOIN FETCH e.livre l JOIN FETCH e.statutPret sp WHERE l.id = :idLivre")
    List<Exemplaire> findByLivreIdWithStatut(@Param("idLivre") Long idLivre);
    

    //     @Query(value = "SELECT * FROM Exemplaire ex JOIN Livre l ON ex.id_livre = l.id JOIN Statut_Pret sp ON ex.id_status = sp.id_statut_pret WHERE l.id = :idLivre", nativeQuery = true)
    // List<Object[]> findExemplairesParLivreSQL(@Param("idLivre") Long idLivre);

}
