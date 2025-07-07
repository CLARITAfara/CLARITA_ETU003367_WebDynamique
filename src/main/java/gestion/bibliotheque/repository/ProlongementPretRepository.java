package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.ProlongementPret;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProlongementPretRepository extends JpaRepository<ProlongementPret, Long> {
}
