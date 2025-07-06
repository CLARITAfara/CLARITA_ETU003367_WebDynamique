package gestion.bibliotheque.service;

import gestion.bibliotheque.model.TypePenalite;
import gestion.bibliotheque.repository.TypePenaliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePenaliteService {

    @Autowired
    private TypePenaliteRepository repository;

    public List<TypePenalite> getAll() {
        return repository.findAll();
    }

    public Optional<TypePenalite> getById(Long id) {
        return repository.findById(id);
    }

    public TypePenalite create(TypePenalite typePenalite) {
        return repository.save(typePenalite);
    }

    public TypePenalite update(Long id, TypePenalite updated) {
        return repository.findById(id).map(existing -> {
            existing.setNomTypePenalite(updated.getNomTypePenalite());
            existing.setDescription(updated.getDescription());
            return repository.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
