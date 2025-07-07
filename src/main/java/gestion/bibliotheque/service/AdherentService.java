package gestion.bibliotheque.service;

import gestion.bibliotheque.model.Adherent;
import gestion.bibliotheque.model.TypeAdherent;

import gestion.bibliotheque.repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    public Optional<Adherent> getAdherentById(Long id) {
        return adherentRepository.findById(id);
    }

    public Adherent createAdherent(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

 


    public TypeAdherent verifRestriction(Long id) throws AdherentRestrictionException {

        Optional<Adherent> optionalAdherent = adherentRepository.findAdherentWithTypeById(id) ;
        
        if (optionalAdherent.isPresent()) {
            Adherent adherent = optionalAdherent.get();
            TypeAdherent typeAdherent = adherent.getTypeAdherent();

            // Vérification des restrictions
            if (adherent.getEstAbonne() == false) {
                throw new AdherentRestrictionException("L'adhérent n'est pas abonné, prêt interdit.");
            }
            if (adherent.getEstPenalise() == true) {
                throw new AdherentRestrictionException("L'adhérent est Penalise.");
            }

            // if (!typeAdherent.getEstPenalise()) {
            //     throw new AdherentRestrictionException("L'adhérent n'est pas abonné, prêt interdit.");
            // }
            return typeAdherent ; // Adhérent abonné, prêt autorisé
        } else {
            throw new AdherentRestrictionException("Aucun adhérent trouvé avec cet ID.");
        }
    }   


    // public boolean verifRestriction( Long id ){

    //     Optional<Adherent> optionalAdherent = adherentRepository.findById(id);
    //     if (optionalAdherent.isPresent()) {
    //         Adherent adherent = optionalAdherent.get();
    //         if(adherent.getEstAbonne() == false ) {
    //             return false; // L'adhérent n'est pas abonné
    //         }
    //         // Utilise l'adherent ici
    //     } else {
    //         // Aucun adhérent trouvé pour cet ID
    //     }
    // }

    // public Adherent updateAdherent(Long id, Adherent updatedAdherent) {
    //     return adherentRepository.findById(id)
    //         .map(existing -> {
    //             existing.setNom(updatedAdherent.getNom());
    //             existing.setPrenom(updatedAdherent.getPrenom());
    //             existing.setDateNaissance(updatedAdherent.getDateNaissance());
    //             existing.setDateInscription(updatedAdherent.getDateInscription());
    //             existing.setIdType(updatedAdherent.getTypeAdherent());
    //             existing.setEstAbonne(updatedAdherent.getEstAbonne());
    //             existing.setEstPenalise(updatedAdherent.getEstPenalise());
    //             return adherentRepository.save(existing);
    //         }).orElse(null);
    // }

    public void deleteAdherent(Long id) {
        adherentRepository.deleteById(id);
    }

    // public List<Adherent> findByNomContaining(String nom) {
    //     return adherentRepository.findByNomContainingIgnoreCase(nom);
    // }

    // public List<Adherent> findPenalisedAdherents() {
    //     return adherentRepository.findByEstPenaliseTrue();
    // }

    // select * 
    // from  Adherent as ad
    // join Type_Adherent as ta on ad.id_type = ta.id
    // where ad.id = 1;
}
