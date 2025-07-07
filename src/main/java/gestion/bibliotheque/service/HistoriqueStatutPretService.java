package gestion.bibliotheque.service;

import gestion.bibliotheque.model.HistoriqueStatutPret;
import gestion.bibliotheque.repository.HistoriqueStatutPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueStatutPretService {

    @Autowired
    private HistoriqueStatutPretRepository repository;

    public List<HistoriqueStatutPret> getAll() {
        return repository.findAll();
    }

    public Optional<HistoriqueStatutPret> getById(Long id) {
        return repository.findById(id);
    }

    public List<HistoriqueStatutPret> getByPretId(Long idPret) {
        return repository.findByPretIdOrderByDateChangementDesc(idPret);
    }

    public HistoriqueStatutPret create(HistoriqueStatutPret historique) {
        return repository.save(historique);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
