package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {
	
	@Autowired PaieUtils p;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		Grade g = bulletin.getRemunerationEmploye().getGrade();
		
		BigDecimal salaireBase = g.getNbHeuresBase().multiply(g.getTauxBase());
		
		BigDecimal salaireBrut = g.getNbHeuresBase().multiply(g.getTauxBase()).add(bulletin.getPrimeExceptionnelle());
		
		BigDecimal retenueSalariale = bulletin.getRemunerationEmploye()
				.getProfilRemuneration()
				.getCotisationsNonImposables()
				.stream()
				.filter(c -> c.getTauxSalarial()!=null)
				.map(c -> c.getTauxSalarial().multiply(salaireBrut))
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		
		BigDecimal cotisationsPatronales = bulletin.getRemunerationEmploye()
				.getProfilRemuneration()
				.getCotisationsNonImposables()
				.stream()
				.filter(c -> c.getTauxPatronal()!=null)
				.map(c -> c.getTauxPatronal().multiply(salaireBrut))
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		
		BigDecimal netImposable = salaireBrut.subtract(retenueSalariale);
		
		BigDecimal netAPayer = netImposable
				.subtract(bulletin.getRemunerationEmploye()
						.getProfilRemuneration()
						.getCotisationsImposables()
						.stream()
						.filter(c -> c.getTauxSalarial()!=null)
						.map(c -> c.getTauxSalarial().multiply(salaireBrut))
						.reduce(BigDecimal::add)
						.orElse(BigDecimal.ZERO)); 
		
		return new ResultatCalculRemuneration(p.formaterBigDecimal(salaireBase), p.formaterBigDecimal(salaireBrut), p.formaterBigDecimal(retenueSalariale), p.formaterBigDecimal(cotisationsPatronales), p.formaterBigDecimal(netImposable), p.formaterBigDecimal(netAPayer));
	}

}
