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

	@Autowired
	PaieUtils p;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		Grade g = bulletin.getRemunerationEmploye().getGrade();
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();
		
		resultat.setSalaireDeBase(p.formaterBigDecimal(g.getNbHeuresBase().multiply(g.getTauxBase())));
		
		resultat.setSalaireBrut(p.formaterBigDecimal(g.getNbHeuresBase().multiply(g.getTauxBase()).add(bulletin.getPrimeExceptionnelle())));
		
		resultat.setTotalRetenueSalarial(p.formaterBigDecimal(bulletin.getRemunerationEmploye()
			.getProfilRemuneration()
			.getCotisationsNonImposables()
			.stream()
			.filter(c -> c.getTauxSalarial()!=null)
			.map(c -> c.getTauxSalarial().multiply(new BigDecimal(resultat.getSalaireBrut())))
			.reduce(BigDecimal::add)
			.orElse(BigDecimal.ZERO))
		);
		
		resultat.setTotalCotisationsPatronales(p.formaterBigDecimal(bulletin.getRemunerationEmploye()
			.getProfilRemuneration()
			.getCotisationsNonImposables()
			.stream()
			.filter(c -> c.getTauxPatronal()!=null)
			.map(c -> c.getTauxPatronal().multiply(new BigDecimal(resultat.getSalaireBrut())))
			.reduce(BigDecimal::add)
			.orElse(BigDecimal.ZERO))
		);
		
		resultat.setNetImposable(p.formaterBigDecimal(new BigDecimal(resultat.getSalaireBrut()).subtract(new BigDecimal(resultat.getTotalRetenueSalarial()))));
		
		resultat.setNetAPayer(p.formaterBigDecimal(new BigDecimal(resultat.getNetImposable())
			.subtract(bulletin.getRemunerationEmploye()
				.getProfilRemuneration()
				.getCotisationsImposables()
				.stream()
				.filter(c -> c.getTauxSalarial()!=null)
				.map(c -> c.getTauxSalarial().multiply(new BigDecimal(resultat.getSalaireBrut())))
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO))
			)
		);
		
		return resultat;
	}

}
