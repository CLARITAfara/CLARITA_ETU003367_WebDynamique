package gestion.bibliotheque.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BonjourController {

    @GetMapping("/main")
    public String direBonjour() {
        return "index1"; // correspond à src/main/resources/templates/index1.html
    }

      @GetMapping("/")
    public String showLoginForm(Model model) {
        return "login/login";
    }

}
