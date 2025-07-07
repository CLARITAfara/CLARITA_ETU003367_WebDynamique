package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.TypeAdherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAdherentRepository extends JpaRepository<TypeAdherent, Long> {
    // Tu peux ajouter des méthodes personnalisées ici
}
