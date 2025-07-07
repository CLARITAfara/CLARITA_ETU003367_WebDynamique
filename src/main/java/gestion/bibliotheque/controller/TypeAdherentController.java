package gestion.bibliotheque.controller;

import gestion.bibliotheque.model.TypeAdherent;
import gestion.bibliotheque.service.TypeAdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/type-adherents")
public class TypeAdherentController {

    private final TypeAdherentService typeAdherentService;

    @Autowired
    public TypeAdherentController(TypeAdherentService typeAdherentService) {
        this.typeAdherentService = typeAdherentService;
    }

    @GetMapping("/ajouter")
    public String showAddForm(Model model) {
        model.addAttribute("typeAdherent", new TypeAdherent());
        return "main"; // le nom de la page HTML Thymeleaf (main.html)
    }

    // Traiter la soumission du formulaire
    @PostMapping("/ajouter")
    public String saveTypeAdherent(@ModelAttribute("typeAdherent") TypeAdherent typeAdherent) {
        typeAdherentService.create(typeAdherent); // ✅ méthode correcte
        return "redirect:/type-adherents/liste";
    }

    @GetMapping("/liste")
    public String listTypeAdherents(Model model) {
        model.addAttribute("types", typeAdherentService.getAll()); 
        return "listeTypeAdherent"; 
    }
}
