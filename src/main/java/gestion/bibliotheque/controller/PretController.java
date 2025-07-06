package gestion.bibliotheque.controller;

 
import gestion.bibliotheque.model.*;
import gestion.bibliotheque.service.*;
import gestion.bibliotheque.repository.*;
 

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/prets")
public class PretController {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private TypePretRepository typePretRepository;

    @Autowired
    private StatutPretRepository statutPretRepository;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private ExemplaireService exemplaireService;


        @Autowired
    private QuotaRepository quotaRepository ;


    @GetMapping("/ajouter")
    public String afficherFormulaireAjoutPret(Model model) {
        model.addAttribute("pret", new Pret());
        model.addAttribute("adherents", adherentRepository.findAll());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("typesPret", typePretRepository.findAll());
        model.addAttribute("statuts", statutPretRepository.findAll());
        return "ajouter_pret";

    }
    @GetMapping("/liste_2")
    public String afficherListePretsStyled(Model model) {
    model.addAttribute("prets", pretRepository.findAll());
    return "Pret/liste_2"; // donc Pret/liste_2.html dans templates/
}
    

  
 
    @GetMapping("/NewPret")
    public String ajouterPret(@RequestParam("idExemplaire") Long idExemplaire, Model model) {
        // System.out.println("ID de l'exemplaire sélectionné : " + idExemplaire);

        model.addAttribute("idExemplaire", idExemplaire);

         model.addAttribute("pret", new Pret());
        model.addAttribute("adherents", adherentRepository.findAll());
        // model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("typesPret", typePretRepository.findAll());
        model.addAttribute("statuts", statutPretRepository.findAll());
        return "ajouter_pret";

        // return "formulaire-pret"; // par exemple
    }

    @GetMapping("/NewPretUser")
    public String ajouterPretUser(@RequestParam("idExemplaire") Long idExemplaire, Model model , HttpSession session ) {
        // System.out.println("ID de l'exemplaire sélectionné : " + idExemplaire);

    Long userId = (Long) session.getAttribute("userId");

    if (userId == null) {
        // Pas connecté : rediriger vers la page de login ou afficher un message
        return "redirect:/login";
    }
    // Charger les données de l'utilisateur via le repository
    Adherent adherent = adherentRepository.findById(userId).orElse(null);
 

        model.addAttribute("idExemplaire", idExemplaire);

        model.addAttribute("pret", new Pret());
        model.addAttribute("adherents_id",  adherent.getId() );
        // model.addAttribute("adherents",  adherent  );    

        // model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("typesPret", typePretRepository.findAll());
        model.addAttribute("statuts", statutPretRepository.findAll());
        return "user/Pret/index";

        // return "formulaire-pret"; // par exemple
    }

    @GetMapping("/liste")
    public String listerPrets(Model model) {
        model.addAttribute("prets", pretRepository.findAll());
        return "liste_prets";
    }


    
    @GetMapping("/listeUser")
    public String listerPretsUser(Model model , HttpSession session ) {
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("prets", pretRepository.findByAdherentId(   userId ) );
        return "user/Pret/liste";
    }

    @PostMapping("/ajouter")
public String enregistrerPret(@ModelAttribute Pret pret,
                              @RequestParam("idExemplaire") Long idExemplaire,
                              // Appliquer la date retournée depuis le formulaire
                              RedirectAttributes redirectAttributes) {

    Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire).orElse(null);
    if (exemplaire == null) {
        System.out.println("Exemplaire non trouvé");
        redirectAttributes.addFlashAttribute("errorMessage", "Exemplaire non trouvé.");
        return "redirect:/livres/liste";
    }

    pret.setExemplaire(exemplaire);
    Adherent adherent = pret.getAdherent();

    if (adherent == null) {
        System.out.println("Adhérent non fourni dans le formulaire");
        redirectAttributes.addFlashAttribute("errorMessage", "Adhérent non fourni dans le formulaire.");
        return "redirect:/livres/liste";
    }

    Livre livre = exemplaire.getLivre();
    if (livre == null) {
        System.out.println("Livre introuvable pour l'exemplaire");
        redirectAttributes.addFlashAttribute("errorMessage", "Livre introuvable pour l'exemplaire.");
        return "redirect:/livres/liste";
    }

    try {
        // Vérification de l'abonnement
        TypeAdherent typeAdherent = adherentService.verifRestriction(adherent.getId());

        // Vérification de l'âge
        int ageAdherent = adherent.getAge();
        int ageRestriction = livre.getAgeRestriction() != null ? livre.getAgeRestriction() : 0;
        System.out.println("--- " + ageAdherent + " - " + ageRestriction + " ---");

        if (ageAdherent < ageRestriction) {
            System.out.println("Âge insuffisant pour emprunter ce livre. ");
            redirectAttributes.addFlashAttribute("errorMessage", "Âge insuffisant pour emprunter ce livre :restriction : " + ageRestriction + " ans."+ " Or Age Aherent : " + ageAdherent + " ans.");
            return "redirect:/livres/liste";
        }

        // Calcul de la date de retour
        int dureePret = typeAdherent.getDureeMaxPret();
        if (pret.getDatePret() != null && pret.getTypePret() != null) {
            pret.setDateRetourPrevue(pret.getDatePret().plusDays(dureePret));
        }

        // Récupérer le quota de l'adhérent
        Quota qx = quotaRepository.findByAdherent(adherent).orElse(null);

        if (qx.getCurrPret() < typeAdherent.getQuotaMaxPret()) {
            // Mise à jour du quota
            quotaRepository.findByAdherent(adherent)
                .ifPresent(quota -> {
                    quota.setCurrPret(quota.getCurrPret() + 1);
                    quotaRepository.save(quota);
                });

                // Récupérer le statut "disponible"
            StatutPret statutDisponible = statutPretRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Statut 'preter' introuvable"));
            
                        // Appliquer la date retournée depuis le formulaire

            // Affecter ce statut au prêt avant sauvegarde
            pret.setStatut(statutDisponible);
            pretRepository.save(pret);
            redirectAttributes.addFlashAttribute("successMessage", "Prêt enregistré avec succès.");
            return "redirect:/prets/liste";

        } else {
            System.out.println("Quota de prêts atteint pour l'adhérent.");
            redirectAttributes.addFlashAttribute("errorMessage", "Quota de prêts atteint pour l'adhérent."+ adherent.getNom() + " " + adherent.getPrenom() + ". Vous avez déjà " + qx.getCurrPret() + " prêts en cours."    );
            return "redirect:/livres/liste";
        }

    } catch (AdherentRestrictionException e) {
        System.out.println("Erreur : " + e.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", "Erreur d'abonnement : " + e.getMessage());
        return "redirect:/livres/liste";
    }


}






 @PostMapping("/ajouterUser")
public String enregistrerPretUser(@ModelAttribute Pret pret,
                              @RequestParam("idExemplaire") Long idExemplaire,
                              @RequestParam("adherents_id") Long adherents_id,
                              // Appliquer la date retournée depuis le formulaire
                              RedirectAttributes redirectAttributes) {

    Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire).orElse(null);
    if (exemplaire == null) {
        System.out.println("Exemplaire non trouvé");
        redirectAttributes.addFlashAttribute("errorMessage", "Exemplaire non trouvé.");
        return "redirect:/livres/listeUser";
    }

    pret.setExemplaire(exemplaire);
    Adherent adherent = adherentRepository.findById(adherents_id).orElse(null) ;

    if (adherent == null) {
        System.out.println("Adhérent non fourni dans le formulaire");
        redirectAttributes.addFlashAttribute("errorMessage", "Adhérent non fourni dans le formulaire.");
        return "redirect:/livres/listeUser";
    }

    Livre livre = exemplaire.getLivre();
    if (livre == null) {
        System.out.println("Livre introuvable pour l'exemplaire");
        redirectAttributes.addFlashAttribute("errorMessage", "Livre introuvable pour l'exemplaire.");
        return "redirect:/livres/listeUser";
    }

    try {
        // Vérification de l'abonnement
        TypeAdherent typeAdherent = adherentService.verifRestriction(adherent.getId());

        // Vérification de l'âge
        int ageAdherent = adherent.getAge();
        int ageRestriction = livre.getAgeRestriction() != null ? livre.getAgeRestriction() : 0;
        System.out.println("--- " + ageAdherent + " - " + ageRestriction + " ---");

        if (ageAdherent < ageRestriction) {
            System.out.println("Âge insuffisant pour emprunter ce livre. ");
            redirectAttributes.addFlashAttribute("errorMessage", "Âge insuffisant pour emprunter ce livre :restriction : " + ageRestriction + " ans."+ " Or Age Aherent : " + ageAdherent + " ans.");
            return "redirect:/livres/listeUser";
        }

        // Calcul de la date de retour
        int dureePret = typeAdherent.getDureeMaxPret();
        if (pret.getDatePret() != null && pret.getTypePret() != null) {
            pret.setDateRetourPrevue(pret.getDatePret().plusDays(dureePret));
        }

        // Récupérer le quota de l'adhérent
        Quota qx = quotaRepository.findByAdherent(adherent).orElse(null);

        if (qx.getCurrPret() < typeAdherent.getQuotaMaxPret()) {
            // Mise à jour du quota
            quotaRepository.findByAdherent(adherent)
                .ifPresent(quota -> {
                    quota.setCurrPret(quota.getCurrPret() + 1);
                    quotaRepository.save(quota);
                });

                // Récupérer le statut "disponible"
            StatutPret statutDisponible = statutPretRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Statut 'preter' introuvable"));
            
                        // Appliquer la date retournée depuis le formulaire

            // Affecter ce statut au prêt avant sauvegarde
            pret.setStatut(statutDisponible);
            pret.setAdherent(adherent);
            exemplaireService.SetExemplaireIndispo(exemplaire);
            pretRepository.save(pret);
            redirectAttributes.addFlashAttribute("successMessage", "Prêt enregistré avec succès.");
            return "redirect:/prets/listeUser";

        } else {
            System.out.println("Quota de prêts atteint pour l'adhérent.");
            redirectAttributes.addFlashAttribute("errorMessage", "Quota de prêts atteint pour l'adhérent."+ adherent.getNom() + " " + adherent.getPrenom() + ". Vous avez déjà " + qx.getCurrPret() + " prêts en cours."    );
            return "redirect:/livres/listeUser";
        }

    } catch (AdherentRestrictionException e) {
        System.out.println("Erreur : " + e.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", "Erreur d'abonnement : " + e.getMessage());
        return "redirect:/livres/listeUser";
    }
}





@GetMapping("/rendrelivre/{id}")
public String rendreLivre(@PathVariable Long id, 
                              @RequestParam("dateRetourReelle") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetourReelle,
                              RedirectAttributes redirectAttributes) {
    Optional<Pret> optionalPret = pretRepository.findById(id);

    if (optionalPret.isEmpty()) {
        redirectAttributes.addFlashAttribute("errorMessage", "Prêt introuvable.");
        return "redirect:/prets/liste";
    }

    Pret pret = optionalPret.get();

    // Marquer la date de retour réelle comme aujourd'hui
    // Mettre à jour le statut si nécessaire (ex : "disponible")
    StatutPret statutDisponible = statutPretRepository.findById(3L)
        .orElseThrow(() -> new RuntimeException("Statut 'disponible' introuvable"));
    Adherent adherent = pret.getAdherent();
            // Récupérer le quota de l'adhérent
    Quota quota = quotaRepository.findByAdherent(adherent).orElse(null);

    if (quota != null) {
        // Décrémenter le quota de prêts en cours
        quota.setCurrPret(quota.getCurrPret() - 1);
        quotaRepository.save(quota);
    } else {
        System.out.println("Quota introuvable pour l'adhérent.");
    }

    // Sauvegarder
    // pret.setDateRetourReelle(LocalDate.now());
    Exemplaire exp = pret.getExemplaire() ; 

    pret.setDateRetourReelle(dateRetourReelle);
    pret.setStatut(statutDisponible);
    pretRepository.save(pret);
    exemplaireService.SetExemplaireDispo(exp) ; 
    redirectAttributes.addFlashAttribute( "successMessage" , "Livre rendu avec succès." );
    return "redirect:/prets/liste";
}


// @PostMapping("/ajouter")
// public String enregistrerPret(@ModelAttribute Pret pret, @RequestParam("idExemplaire") Long idExemplaire) {

//     Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire).orElse(null);
//     if (exemplaire == null) {
//         System.out.println("Exemplaire non trouvé");
//         return "redirect:/livres/liste"; // ou afficher une erreur
//     }

//     pret.setExemplaire(exemplaire);
//     Adherent adherent = pret.getAdherent();

//     if (adherent == null) {
//         System.out.println("Adhérent non fourni dans le formulaire");
//         return "redirect:/livres/liste"; // ou afficher une erreur
//     }

//     Livre livre = exemplaire.getLivre();
//     if (livre == null) {
//         System.out.println("Livre introuvable pour l'exemplaire");
//         return "redirect:/livres/liste";
//     }

//     try {
//         // Vérification de l'abonnement
//         TypeAdherent typeAdherent = adherentService.verifRestriction(adherent.getId());

//         // Vérification de l'âge
//         int ageAdherent = adherent.getAge(); // méthode getAge() doit être définie dans Adherent
//         int ageRestriction = livre.getAgeRestriction() != null ? livre.getAgeRestriction() : 0;
//             System.out.println("--- "+ageAdherent +" - "+ageRestriction+" ---" );

//         if (ageAdherent < ageRestriction) {
//             System.out.println("Âge insuffisant pour emprunter ce livre." );
//             return "redirect:/livres/liste";
//         }

//         // Calculer la date de retour prévue
//         int dureePret = typeAdherent.getDureeMaxPret(); // assure-toi qu'elle existe
//         if (pret.getDatePret() != null && pret.getTypePret() != null) {
//             pret.setDateRetourPrevue(pret.getDatePret().plusDays(dureePret));
//         }
//         // Récupérer le quota de l'adhérent (Optional)
//         Quota qx = quotaRepository.findByAdherent(adherent).orElse(null);

//         if (qx.getCurrPret() < typeAdherent.getQuotaMaxPret() ) {
//             // Vérifier si l'adhérent n'est pas déjà pénalisé
//                    // Sauvegarder
//             quotaRepository.findByAdherent(adherent)
//                 .ifPresent(quota -> {
//                     quota.setCurrPret(quota.getCurrPret() + 1);
//                     quotaRepository.save(quota);
//                 });
//             pretRepository.save(pret);
//             return "redirect:/prets/liste"; 
//         } else {
//             System.out.println("Quota de prêts atteint pour l'adhérent.");
//             return "redirect:/livres/liste";

//         }

//     } catch (AdherentRestrictionException e) {
//         System.out.println("Erreur : " + e.getMessage());
//         return "redirect:/livres/liste";
//     }
// }




}


 