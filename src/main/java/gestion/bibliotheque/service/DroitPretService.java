package gestion.bibliotheque.service;

import gestion.bibliotheque.model.DroitPret;
import gestion.bibliotheque.repository.DroitPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroitPretService {

    @Autowired
    private DroitPretRepository repository;

    public List<DroitPret> getAll() {
        return repository.findAll();
    }

    public Optional<DroitPret> getById(Long id) {
        return repository.findById(id);
    }

    public DroitPret create(DroitPret droitPret) {
        return repository.save(droitPret);
    }

    public DroitPret update(Long id, DroitPret updated) {
        return repository.findById(id).map(existing -> {
            existing.setTypeAdherent(updated.getTypeAdherent());
            existing.setTypeUtilisation(updated.getTypeUtilisation());
            existing.setLivre(updated.getLivre());
            existing.setAutorise(updated.getAutorise());
            return repository.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<DroitPret> getDroitsAutorises() {
        return repository.findByAutoriseTrue();
    }
}
