package gestion.bibliotheque.service;

import gestion.bibliotheque.model.StatutPret;
import gestion.bibliotheque.repository.StatutPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutPretService {

    @Autowired
    private StatutPretRepository statutPretRepository;

    public List<StatutPret> getAll() {
        return statutPretRepository.findAll();
    }

    public Optional<StatutPret> getById(Long id) {
        return statutPretRepository.findById(id);
    }

    public Optional<StatutPret> getByNom(String nom) {
        return statutPretRepository.findByNomStatut(nom);
    }

    public StatutPret create(StatutPret statutPret) {
        return statutPretRepository.save(statutPret);
    }

    public StatutPret update(Long id, StatutPret updated) {
        return statutPretRepository.findById(id).map(existing -> {
            existing.setNomStatut(updated.getNomStatut());
            return statutPretRepository.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        statutPretRepository.deleteById(id);
    }
}