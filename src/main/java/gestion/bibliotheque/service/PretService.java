package gestion.bibliotheque.service;

import gestion.bibliotheque.model.Pret;
import gestion.bibliotheque.repository.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;

    public List<Pret> getAll() {
        return pretRepository.findAll();
    }

    public Optional<Pret> getById(Long id) {
        return pretRepository.findById(id);
    }

    public Pret create(Pret pret) {
        return pretRepository.save(pret);
    }

    public Pret update(Long id, Pret updated) {
        return pretRepository.findById(id).map(existing -> {
            existing.setAdherent(updated.getAdherent());
            existing.setExemplaire(updated.getExemplaire());
            existing.setDatePret(updated.getDatePret());
            existing.setDateRetourPrevue(updated.getDateRetourPrevue());
            existing.setDateRetourReelle(updated.getDateRetourReelle());
            existing.setTypePret(updated.getTypePret());
            existing.setStatut(updated.getStatut());
            return pretRepository.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        pretRepository.deleteById(id);
    }

    public List<Pret> getPretsParAdherent(Long idAdherent) {
        return pretRepository.findByAdherentId(idAdherent);
    }

    public List<Pret> getPretsEnCours() {
        return pretRepository.findByDateRetourReelleIsNull();
    }

    public List<Pret> getPretsParStatut(String statut) {
        return pretRepository.findByStatutNomStatut(statut);
    }
}
