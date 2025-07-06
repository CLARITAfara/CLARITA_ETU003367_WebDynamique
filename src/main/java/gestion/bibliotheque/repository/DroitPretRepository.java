package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.DroitPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroitPretRepository extends JpaRepository<DroitPret, Long> {
    List<DroitPret> findByTypeAdherentId(Long typeAdherentId);
    List<DroitPret> findByTypeUtilisationId(Long typeUtilisationId);
    List<DroitPret> findByLivreId(Long livreId);
    List<DroitPret> findByAutoriseTrue();
}
