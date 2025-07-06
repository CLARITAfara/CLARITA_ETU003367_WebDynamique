package gestion.bibliotheque.repository;

import gestion.bibliotheque.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Optional<Admin> findByNomUtilisateurAndMotDePasse(String nomUtilisateur, String motDePasse);

    Optional<Admin> findByNomUtilisateur(String nomUtilisateur);
    Optional<Admin> findByMotDePasse(String motDePasse);
}
