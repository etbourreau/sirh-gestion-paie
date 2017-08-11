package dev.paie.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/connexion")
public class ConnexionController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String afficherPageCreer(Model m){
		m.addAttribute("titre", "Connexion");
		return "connexion";
	}
	
}
