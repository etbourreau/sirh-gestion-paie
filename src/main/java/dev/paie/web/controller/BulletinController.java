package dev.paie.web.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.BulletinRepository;
import dev.paie.repository.EmployeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.service.BulletinService;

@Controller
@RequestMapping("/bulletins")
public class BulletinController {

	@Autowired
	private BulletinRepository bulletins;
	@Autowired
	BulletinService bulletinsService;
	@Autowired
	private PeriodeRepository periodes;
	@Autowired
	private EmployeRepository employes;

	@RequestMapping(method = RequestMethod.GET)
	@Secured({"ROLE_ADMINISTRATEUR", "ROLE_UTILISATEUR"})
	public ModelAndView listerEmployes() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");
		mv.addObject("titre", "Liste des bulletins");
		mv.addObject("listeBulletins", bulletinsService.lister());
		return mv;

	}

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured({"ROLE_ADMINISTRATEUR"})
	public String creerBulletin(Model m) {
		m.addAttribute("titre", "Créer un bulletin");
		m.addAttribute("listePeriodes", periodes.findAll());
		m.addAttribute("listeEmployes", employes.findAll());
		return "bulletins/creerBulletin";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured({"ROLE_ADMINISTRATEUR"})
	public String creerBulletin(@RequestParam("periode") Integer idPeriode,
			@RequestParam("employe") Integer idEmploye,
			@RequestParam("prime") String prime) {
		if(prime.isEmpty() || idPeriode == null || idEmploye == null){
			return "bulletins/creerBulletin?error=";
		}else{
			BulletinSalaire b = new BulletinSalaire();
			b.setPeriode(periodes.findOne(idPeriode));
			b.setRemunerationEmploye(employes.findOne(idEmploye));
			b.setPrimeExceptionnelle(new BigDecimal(prime));
			b.setDateHeureCreation(LocalDateTime.now());
			bulletins.save(b);
			return "redirect:/mvc/bulletins";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, path = "/visualiser/{id}")
	@Secured({"ROLE_ADMINISTRATEUR", "ROLE_UTILISATEUR"})
	public String visualiserBulletin(@PathVariable Integer id){
		if(bulletins.findOne(id)==null){
			return "redirect:/mvc/bulletins";
		}else{
			
			return "bulletins/visualiserBulletin";
		}
	}

}
