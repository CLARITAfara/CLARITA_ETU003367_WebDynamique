package gestion.bibliotheque.service;

import gestion.bibliotheque.model.TypeUtilisation;
import gestion.bibliotheque.repository.TypeUtilisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeUtilisationService {

    @Autowired
    private TypeUtilisationRepository repository;

    public List<TypeUtilisation> getAllTypes() {
        return repository.findAll();
    }

    public Optional<TypeUtilisation> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<TypeUtilisation> getByNom(String nom) {
        return repository.findByNomTypeUtilisation(nom);
    }

    public TypeUtilisation createType(TypeUtilisation type) {
        return repository.save(type);
    }

    public TypeUtilisation updateType(Long id, TypeUtilisation updatedType) {
        return repository.findById(id).map(existing -> {
            existing.setNomTypeUtilisation(updatedType.getNomTypeUtilisation());
            return repository.save(existing);
        }).orElse(null);
    }

    public void deleteType(Long id) {
        repository.deleteById(id);
    }
}
