package gestion.bibliotheque.service;

import gestion.bibliotheque.model.Livre;
import gestion.bibliotheque.model.Adherent;

import gestion.bibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    public List<Livre> searchByAuteur(String auteur) {
        return livreRepository.findByAuteurContainingIgnoreCase(auteur);
    }


    //  public void deleteLivre(Adherant adherent ) {
    //     if(adherent.getAge()  )
    //     livreRepository.deleteById(id);
    // }


}
