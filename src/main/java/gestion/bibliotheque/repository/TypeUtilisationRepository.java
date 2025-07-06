package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.TypeUtilisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeUtilisationRepository extends JpaRepository<TypeUtilisation, Long> {
    Optional<TypeUtilisation> findByNomTypeUtilisation(String nomTypeUtilisation);
}
