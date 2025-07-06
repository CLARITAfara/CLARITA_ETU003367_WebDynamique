package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.Quota;
import gestion.bibliotheque.model.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuotaRepository extends JpaRepository<Quota, Long> {

    // Déclaration de la méthode de recherche par Adherent
    Optional<Quota> findByAdherent(Adherent adherent);

    // Vous pouvez aussi garder la méthode par id d'Adherent si vous voulez :
    Quota findByAdherentId(Long idAdherent);
}
