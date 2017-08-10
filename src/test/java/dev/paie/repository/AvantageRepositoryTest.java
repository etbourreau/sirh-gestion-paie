package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {
	
	@Autowired private AvantageRepository avantageRepository;
	
	@Before
	public void setUp(){
		System.out.println(avantageRepository);
	}
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	// TODO sauvegarder un nouvel avantage
		avantageRepository.save(new Avantage("code", "nom", new BigDecimal("1")));
	// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		Avantage a = avantageRepository.findAll().get(0);
		assertThat(a.getCode()).isEqualTo("code");
	// TODO modifier un avantage
		a.setCode("nouveauCode");
		a.setNom("nouveauNom");
		a.setMontant(new BigDecimal("2"));
		avantageRepository.save(a);
	// TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
		assertThat(a.getCode()).isEqualTo("nouveauCode");
		assertThat(a.getNom()).isEqualTo("nouveauNom");
		assertThat(a.getMontant()).isEqualTo(new BigDecimal("2"));
	}

}
