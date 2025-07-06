package gestion.bibliotheque.controller;

 
import gestion.bibliotheque.model.TypePayement;
import gestion.bibliotheque.repository.TypePayementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/type-payements")
public class TypePayementController {

    @Autowired
    private TypePayementRepository typePayementRepository;

    @GetMapping("/liste")
    public String listTypePayements(Model model) {
        model.addAttribute("typePayements", typePayementRepository.findAll());
        return "payement_list";
    }

    @GetMapping("/ajouter")
    public String formAjouterTypePayement(Model model) {
        model.addAttribute("typePayement", new TypePayement());
        return "ajouter_payement";
    }

    @PostMapping("/ajouter")
    public String ajouterTypePayement(@ModelAttribute TypePayement typePayement) {
        typePayementRepository.save(typePayement);
        return "redirect:/type-payements/liste";
    }

    
}
