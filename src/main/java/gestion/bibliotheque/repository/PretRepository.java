package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PretRepository extends JpaRepository<Pret, Long> {
    List<Pret> findByAdherentId(Long idAdherent);
    List<Pret> findByStatutNomStatut(String nomStatut);
    List<Pret> findByDateRetourReelleIsNull(); // Prêts en cours
}
