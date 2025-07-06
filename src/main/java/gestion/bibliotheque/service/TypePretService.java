package gestion.bibliotheque.service;

import gestion.bibliotheque.model.TypePret;
import gestion.bibliotheque.repository.TypePretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePretService {

    @Autowired
    private TypePretRepository typePretRepository;

    public List<TypePret> getAll() {
        return typePretRepository.findAll();
    }

    public Optional<TypePret> getById(Long id) {
        return typePretRepository.findById(id);
    }

    public TypePret create(TypePret typePret) {
        return typePretRepository.save(typePret);
    }

    public TypePret update(Long id, TypePret updatedType) {
        return typePretRepository.findById(id).map(existing -> {
            existing.setNomTypePret(updatedType.getNomTypePret());
            existing.setDureeMax(updatedType.getDureeMax());
            return typePretRepository.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        typePretRepository.deleteById(id);
    }
}
