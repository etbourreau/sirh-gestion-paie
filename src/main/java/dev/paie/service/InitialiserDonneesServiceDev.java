package dev.paie.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Service
@EnableTransactionManagement
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired
	private EntityManager em;

	@Override
	public void initialiser() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("grades.xml", "entreprises.xml",
				"profils-remuneration.xml", "cotisations-imposables.xml", "cotisations-non-imposables.xml");
		Stream.of(Grade.class, Entreprise.class, Cotisation.class, ProfilRemuneration.class)
				.forEach(c -> 
					context.getBeansOfType(c)
						.values()
						.stream()
						.forEach(o -> em.persist(o))
				);
		
		List<Periode> periods = new ArrayList<>();
		periods.add(new Periode(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31)));
		periods.add(new Periode(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28)));
		periods.add(new Periode(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31)));
		periods.add(new Periode(LocalDate.of(2017, 4, 1), LocalDate.of(2017, 4, 30)));
		periods.add(new Periode(LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 31)));
		periods.add(new Periode(LocalDate.of(2017, 6, 1), LocalDate.of(2017, 6, 30)));
		periods.add(new Periode(LocalDate.of(2017, 7, 1), LocalDate.of(2017, 7, 31)));
		periods.add(new Periode(LocalDate.of(2017, 8, 1), LocalDate.of(2017, 8, 31)));
		periods.add(new Periode(LocalDate.of(2017, 9, 1), LocalDate.of(2017, 9, 30)));
		periods.add(new Periode(LocalDate.of(2017, 10, 1), LocalDate.of(2017, 10, 31)));
		periods.add(new Periode(LocalDate.of(2017, 11, 1), LocalDate.of(2017, 11, 30)));
		periods.add(new Periode(LocalDate.of(2017, 12, 1), LocalDate.of(2017, 12, 31)));
		
		periods.stream().forEach(p -> em.persist(p));
		
		context.close();

	}

}
