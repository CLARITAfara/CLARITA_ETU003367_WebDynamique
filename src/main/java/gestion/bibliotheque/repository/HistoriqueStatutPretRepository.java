package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.HistoriqueStatutPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriqueStatutPretRepository extends JpaRepository<HistoriqueStatutPret, Long> {
    List<HistoriqueStatutPret> findByPretIdOrderByDateChangementDesc(Long idPret);
}
