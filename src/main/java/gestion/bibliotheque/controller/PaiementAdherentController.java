package gestion.bibliotheque.controller;

 
import gestion.bibliotheque.model.*;
import gestion.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paiements")
public class PaiementAdherentController {

    @Autowired private PaiementAdherentRepository paiementRepo;
    @Autowired private AdherentRepository adherentRepo;
    @Autowired private TypePayementRepository typeRepo;
    @Autowired private StatutPaiementRepository statutRepo;

    @GetMapping
    public String listePaiements(Model model) {
        model.addAttribute("paiements", paiementRepo.findAll());
        return "liste_paiement";
    }

    @GetMapping("/ajouter")
    public String formAjoutPaiement(Model model) {
        model.addAttribute("paiement", new PaiementAdherent());
        model.addAttribute("adherents", adherentRepo.findAll());
        model.addAttribute("types", typeRepo.findAll());
        model.addAttribute("statuts", statutRepo.findAll());
        return "ajout_paie";
    }

    @PostMapping("/ajouter")
    public String enregistrerPaiement(@ModelAttribute PaiementAdherent paiement) {
        try {
            TypePayement typePayement = paiement.getTypePayement() ; 
            double montant  = typePayement.getMontantPayement() ; 
            paiement.setMontantPaye(montant); // Set the montantPaye based on the typePayement
            Adherent adherent = paiement.getAdherent();
            adherent.setEstAbonne(true); // Set the adherent as subscribed
        } catch (Exception e) {
            // TODO: handle exception
        }
        paiementRepo.save(paiement);
        return "redirect:/paiements";
    }

}
