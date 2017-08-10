package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {

	@Autowired
	private CotisationService cotisationService;
	
	@Before
	public void setUp(){
	}

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder une nouvelle cotisation
		cotisationService.sauvegarder(
				new Cotisation("cotisationCode", "cotisationLibelle", new BigDecimal("1"), new BigDecimal("1")));
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
		// via la méthode lister
		Cotisation c = cotisationService.lister().get(0);
		assertThat(c.getCode()).isEqualTo("cotisationCode");
		// TODO modifier une cotisation
		c.setCode("cotisationNouveauCode");
		c.setLibelle("cotisationNouveauLibelle");
		c.setTauxSalarial(new BigDecimal("1.1"));
		c.setTauxPatronal(new BigDecimal("1.2"));
		cotisationService.mettreAJour(c);
		// TODO vérifier que les modifications sont bien prises en compte via la
		// méthode lister
		assertThat(c.getCode()).isEqualTo("cotisationNouveauCode");
		assertThat(c.getLibelle()).isEqualTo("cotisationNouveauLibelle");
		assertThat(c.getTauxSalarial()).isEqualTo(new BigDecimal("1.1"));
		assertThat(c.getTauxPatronal()).isEqualTo(new BigDecimal("1.2"));
	}

}
