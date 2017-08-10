package dev.paie.service;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		String sql = "INSERT INTO grade(id, code, nbHeuresBase, tauxBase) VALUES(:id, :code, :heures, :taux)";
		
		Map<String,String> params = new HashMap<>();
		params.put("id", null);
		params.put("code", nouveauGrade.getCode());
		params.put("heures", nouveauGrade.getNbHeuresBase().toString());
		params.put("taux", nouveauGrade.getTauxBase().toString());
		
		this.jdbcTemplate.update(sql, params);
	}

	@Override
	public void mettreAJour(Grade grade) {
		String sql = "UPDATE grade SET code=:code, nbHeuresBase=:heures, tauxBase=:taux WHERE id=:id";
		
		Map<String,String> params = new HashMap<>();
		params.put("id", grade.getId().toString());
		params.put("code", grade.getCode());
		params.put("heures", grade.getNbHeuresBase().toString());
		params.put("taux", grade.getTauxBase().toString());
		
		this.jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Grade> lister() {
		String sql = "SELECT * FROM grade";
		return this.jdbcTemplate.query(sql, (rs,rn) -> new Grade(rs.getInt("id"), rs.getString("code"), rs.getBigDecimal("nbHeuresBase"), rs.getBigDecimal("tauxBase")));
	}

	@Override
	public void truncate() {
		String sql = "TRUNCATE grade";
		this.jdbcTemplate.execute(sql, PreparedStatement::executeUpdate);
	}

	@Override
	public Grade findOne(Integer id) {
		return this.lister().stream().filter(g -> g.getId()==id).findAny().orElse(null);
	}

}
