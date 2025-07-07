package gestion.bibliotheque.controller;

import gestion.bibliotheque.service.ProlongementPretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/prolongements")
public class ProlongementPretController {

    @Autowired
    private ProlongementPretService prolongementPretService;

    // Demander un prolongement pour un prêt donné
    @PostMapping("/demander")
    public String demanderProlongement(@RequestParam("pretId") Long pretId, RedirectAttributes redirectAttributes) {
        boolean success = prolongementPretService.demanderProlongement(pretId);

        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "Prolongement demandé avec succès.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Impossible de demander un prolongement. Quota dépassé ou prêt déjà prolongé.");
        }

        return "redirect:/prets/liste"; // Redirige vers la liste des prêts
    }
}
