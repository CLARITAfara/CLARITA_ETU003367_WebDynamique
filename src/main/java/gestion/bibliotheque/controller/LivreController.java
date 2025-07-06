package gestion.bibliotheque.controller;

import gestion.bibliotheque.model.Livre;
import gestion.bibliotheque.model.StatutPret;
import gestion.bibliotheque.model.Exemplaire;

import gestion.bibliotheque.repository.LivreRepository;
import gestion.bibliotheque.repository.ExemplaireRepository; 
import gestion.bibliotheque.repository.StatutPretRepository ; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;


@Controller
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;
     @Autowired
    private ExemplaireRepository exemplaireRepository ;
      @Autowired
    private StatutPretRepository statutPretRepository ;

    

    @GetMapping("/ajouter")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("livre", new Livre());
        return "ajouterLivre";
    }

// @PostMapping("/ajouter")
// public String ajouterLivre(@ModelAttribute Livre livre) {
//     livreRepository.save(livre);

//     // Crée les exemplaires ici avec le nombre contenu dans `livre.getNbrExmp()`
//     for (int i = 0; i < livre.getNbrExmp(); i++) {
//         Exemplaire ex = new Exemplaire();
//         ex.setLivre(livre);
//         ex.setCodeExemplaire("LV_"+ livre.getId()+"-EX_" + (i + 1) );
//         exemplaireRepository.save(ex);
//     }

//     return "redirect:/livres/liste";
// }


@PostMapping("/ajouter")
public String ajouterLivre(@ModelAttribute Livre livre, 
                          @RequestParam("imageFile") MultipartFile imageFile) {
    if (!imageFile.isEmpty()) {
        try {
            // Dossier à côté du projet ou du .jar
            String dossierImages = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";
            Path path = Paths.get(dossierImages);
            if (!Files.exists(path)) {
                    Files.createDirectories(path);
        }

            // Nom du fichier (tu peux rajouter un timestamp pour éviter les doublons)
            String nomFichier = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();

            // Chemin complet
            Path cheminFichier = path.resolve(nomFichier);

            // Sauvegarde le fichier
            Files.copy(imageFile.getInputStream(), cheminFichier, StandardCopyOption.REPLACE_EXISTING);

            // Enregistre le chemin dans l’objet Livre
            livre.setImageUrl( nomFichier);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sauvegarde le livre dans la base
    livreRepository.save(livre);

    // Crée les exemplaires ici avec le nombre contenu dans `livre.getNbrExmp()`
    for (int i = 0; i < livre.getNbrExmp(); i++) {
        Exemplaire ex = new Exemplaire();
        ex.setLivre(livre);
        ex.setCodeExemplaire("LV_"+ livre.getId()+"-EX_" + (i + 1) );
        StatutPret statut = statutPretRepository.findById(1L).orElse(null);
        ex.setStatutPret(statut);
        exemplaireRepository.save(ex);
    }

    return "redirect:/livres/liste";
}



    @GetMapping("/liste")
    public String listeLivres(Model model) {
        model.addAttribute("livres", livreRepository.findAll());
        return "listeLivres";
    }


     
    @GetMapping("/livre/{id}")
    public String listeExemplairesParLivre(@PathVariable Long id, Model model) {
       List<Exemplaire> exemplaires = exemplaireRepository.findByLivreIdWithStatut(id);
            model.addAttribute("exemplaires", exemplaires);
            return "liste_exemplaires";
    }


    @GetMapping("/listeUser")
    public String listeLivresUser(Model model) {
        model.addAttribute("livres", livreRepository.findAll());
        return "user/Livre/index";
    }

    @GetMapping("/livreUser/{id}")
    public String listeExemplairesParLivreUser(@PathVariable Long id, Model model) {
       List<Exemplaire> exemplaires = exemplaireRepository.findByLivreIdWithStatut(id);
            model.addAttribute("exemplaires", exemplaires);
            return "user/Livre/liste_exemplaires";
    }

}
