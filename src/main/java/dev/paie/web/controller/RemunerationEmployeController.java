package dev.paie.web.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EmployeRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.service.GradeService;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	private EmployeRepository employes;
	@Autowired
	private EntrepriseRepository entreprises;
	@Autowired
	private ProfilRemunerationRepository profils;
	@Autowired
	private GradeService grades;

	@RequestMapping(method = RequestMethod.GET)
	@Secured({"ROLE_ADMINISTRATEUR", "ROLE_UTILISATEUR"})
	public ModelAndView listerEmployes() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmployes");
		mv.addObject("titre", "Liste des employés");
		mv.addObject("listeEmployes", employes.findAll());
		return mv;

	}

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured({"ROLE_ADMINISTRATEUR"})
	public String creerEmploye(Model m) {
		m.addAttribute("titre", "Créer un employé");
		m.addAttribute("listeEntreprises", entreprises.findAll());
		m.addAttribute("listeProfils", profils.findAll());
		m.addAttribute("listeGrades", grades.lister());
		return "employes/creerEmploye";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured({"ROLE_ADMINISTRATEUR"})
	public String creerEmploye(@RequestParam("matricule") String matricule,
			@RequestParam("profil") Integer pr,
			@RequestParam("grade") Integer g,
			@RequestParam("entreprise") Integer e) {
		if(matricule.isEmpty()){
			return "employes/creerEmploye?error=";
		}else{
			RemunerationEmploye employe = new RemunerationEmploye();
			employe.setMatricule(matricule);
			employe.setEntreprise(entreprises.findOne(e));
			employe.setProfilRemuneration(profils.findOne(pr));
			employe.setGrade(grades.findOne(g));
			employe.setDateHeureCreation(LocalDateTime.now());
			employes.save(employe);
			return "redirect:/mvc/employes";
		}
	}

}
