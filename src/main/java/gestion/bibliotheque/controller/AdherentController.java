package gestion.bibliotheque.controller;

import gestion.bibliotheque.model.TypeAdherent;
import gestion.bibliotheque.model.Adherent;
import gestion.bibliotheque.model.Admin;
import gestion.bibliotheque.model.Quota;
import gestion.bibliotheque.service.TypeAdherentService;
import gestion.bibliotheque.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;
import jakarta.servlet.http.HttpSession;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/adherents")
public class AdherentController {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private TypeAdherentRepository typeAdherentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private QuotaRepository quotaRepository ;



@PostMapping("/login")
public String processLogin(@RequestParam String username,
                           @RequestParam String password,
                           Model model,
                           HttpSession session) {

    // Chercher l'adhérent
    Adherent adherent = adherentRepository.findByNom(username);

    // Chercher l'administrateur
    Admin admin = adminRepository.findByNomUtilisateur(username).orElse(null);

    // Vérification Adhérent
    if (adherent != null && adherent.getPassword().equals(password)) {
        session.setAttribute("userId", adherent.getId());
        model.addAttribute("adherent", adherent);
        return "redirect:/livres/listeUser";  // Rediriger vers la page utilisateur
    }

    // Vérification Admin
    else if (admin != null && admin.getMotDePasse().equals(password)) {
        session.setAttribute("AdminId", admin.getId());
        model.addAttribute("admin", admin);
        return "admin/index1";  // Rediriger vers la page admin
    }

    // Sinon, identifiants incorrects
    else {
        model.addAttribute("errorMessage", "Nom d'utilisateur ou mot de passe incorrect");
        return "login/login";  // Retour au formulaire de login
    }
}


    @GetMapping("/ajouter")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("adherent", new Adherent());
        model.addAttribute("types", typeAdherentRepository.findAll());
        return "user";
    }

    @PostMapping("/ajouter")
    public String ajouterAdherent(@ModelAttribute Adherent adherent) {
        adherentRepository.save(adherent);
        Quota quota = new Quota();
        quota.setAdherent(adherent);
        quota.setCurrPret(0);
        quota.setCurrProlongement(0);
        quotaRepository.save(quota); // Enregistrer le quota associé à l'adhérent
        return "redirect:listeAdherent"; // ou vers la page d'accueil
    }

    @GetMapping("/listeAdherent")
    public String listeAdherents(Model model) {
        model.addAttribute("adherents", adherentRepository.findAll());
        return "listeAdherent";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(TypeAdherent.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Long id = Long.parseLong(text);
                TypeAdherent type = typeAdherentRepository.findById(id).orElse(null);
                setValue(type);
            }
        });
    }
}
