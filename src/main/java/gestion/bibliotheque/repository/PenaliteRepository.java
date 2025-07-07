package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaliteRepository extends JpaRepository<Penalite, Long> {
    List<Penalite> findByAdherentId(Long adherentId);
}
