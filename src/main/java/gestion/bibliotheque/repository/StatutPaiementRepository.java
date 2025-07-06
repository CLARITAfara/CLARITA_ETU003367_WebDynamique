package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.StatutPaiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutPaiementRepository extends JpaRepository<StatutPaiement, Long> {

}