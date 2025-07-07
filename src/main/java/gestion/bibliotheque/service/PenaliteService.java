package gestion.bibliotheque.service;

import gestion.bibliotheque.model.Penalite;
import gestion.bibliotheque.repository.PenaliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenaliteService {

    @Autowired
    private PenaliteRepository repository;

    public List<Penalite> getAll() {
        return repository.findAll();
    }

    public Optional<Penalite> getById(Long id) {
        return repository.findById(id);
    }

    public List<Penalite> getByAdherent(Long adherentId) {
        return repository.findByAdherentId(adherentId);
    }

    public Penalite create(Penalite penalite) {
        return repository.save(penalite);
    }

    public Penalite update(Long id, Penalite updated) {
        return repository.findById(id).map(existing -> {
            existing.setAdherent(updated.getAdherent());
            existing.setTypePenalite(updated.getTypePenalite());
            existing.setDateDebut(updated.getDateDebut());
            existing.setDateFin(updated.getDateFin());
            return repository.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
