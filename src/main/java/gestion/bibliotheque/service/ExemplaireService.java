package gestion.bibliotheque.service;

import gestion.bibliotheque.model.*;
import gestion.bibliotheque.repository.*;

import gestion.bibliotheque.repository.ExemplaireRepository;
import gestion.bibliotheque.repository.StatutPretRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    StatutPretRepository statusRepository ;  

    public List<Exemplaire> getAll() {
        return exemplaireRepository.findAll();
    }

    public Optional<Exemplaire> getById(Long id) {
        return exemplaireRepository.findById(id);
    }

    public Optional<Exemplaire> getByCode(String code) {
        return exemplaireRepository.findByCodeExemplaire(code);
    }

    public List<Exemplaire> getByLivreId(Long livreId) {
        return exemplaireRepository.findByLivreId(livreId);
    }

    public Exemplaire create(Exemplaire exemplaire) {
        return exemplaireRepository.save(exemplaire);
    }

    public Exemplaire update(Long id, Exemplaire updated) {
        return exemplaireRepository.findById(id).map(existing -> {
            existing.setCodeExemplaire(updated.getCodeExemplaire());
            existing.setLivre(updated.getLivre());
            return exemplaireRepository.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        exemplaireRepository.deleteById(id);
    }


    public void SetExemplaireDispo(Exemplaire exemplaire) {
      StatutPret sp =  statusRepository.findById(1L).orElse(null) ;
      exemplaire.setStatutPret( sp ) ;
      exemplaireRepository.save(exemplaire); // <- Sauvegarde en base
    }

    public void SetExemplaireIndispo(Exemplaire exemplaire) {
      StatutPret sp =  statusRepository.findById(2L).orElse(null) ;
      exemplaire.setStatutPret( sp ) ;
      exemplaireRepository.save(exemplaire); // <- Sauvegarde en base
    }


    
}
