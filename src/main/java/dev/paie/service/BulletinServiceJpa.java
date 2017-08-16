package dev.paie.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinRepository;

@Service
public class BulletinServiceJpa implements BulletinService {
	
	@Autowired
	BulletinRepository bulletins;
	@Autowired
	CalculerRemunerationServiceSimple calcul;
	
	@Transactional(value=TxType.REQUIRED)
	@Override
	public Map<BulletinSalaire, ResultatCalculRemuneration> lister() {
		Map<BulletinSalaire, ResultatCalculRemuneration> listeBulletins = new HashMap<>();
		bulletins.findAll().stream().forEach(b -> listeBulletins.put(b, calcul.calculer(b)));
		return listeBulletins;
	}

}
