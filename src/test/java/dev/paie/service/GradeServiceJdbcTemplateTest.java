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
import dev.paie.entite.Grade;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	@Autowired
	private GradeService gradeService;
	
	@Before
	public void setUp(){
	}

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouveau grade
		gradeService.sauvegarder(new Grade(1, "CDP", new BigDecimal("35"), new BigDecimal("9")));
		
		// TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		assertThat(gradeService.lister().size()).isEqualTo(1);
		
		// TODO modifier un grade
		gradeService.mettreAJour(new Grade(1, "RDP", new BigDecimal("32"), new BigDecimal("9.5")));
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		assertThat(gradeService.lister().get(0).getCode()).isEqualTo("RDP");
		assertThat(gradeService.lister().get(0).getNbHeuresBase()).isEqualTo("32.00");
		assertThat(gradeService.lister().get(0).getTauxBase()).isEqualTo("9.50");
		
	}

}
