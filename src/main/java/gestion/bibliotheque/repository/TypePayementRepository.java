package gestion.bibliotheque.repository;


import gestion.bibliotheque.model.TypePayement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePayementRepository extends JpaRepository<TypePayement, Long> {
}
