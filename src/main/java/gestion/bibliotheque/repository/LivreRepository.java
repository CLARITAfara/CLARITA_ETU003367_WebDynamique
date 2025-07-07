package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    List<Livre> findByAuteurContainingIgnoreCase(String auteur);
    List<Livre> findByTitreContainingIgnoreCase(String titre);
    Livre findByIsbn(String isbn);
}
