package dev.paie.entite;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dev.paie.util.PaieUtils;

@Entity
public class Grade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private BigDecimal nbHeuresBase;
	private BigDecimal tauxBase;
	
	public Grade() {
		super();
	}

	public Grade(Integer id, String code, BigDecimal nbHeuresBase, BigDecimal tauxBase) {
		super();
		this.id = id;
		this.code = code;
		this.nbHeuresBase = nbHeuresBase;
		this.tauxBase = tauxBase;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public BigDecimal getNbHeuresBase() {
		return nbHeuresBase;
	}
	
	public void setNbHeuresBase(BigDecimal nbHeuresBase) {
		this.nbHeuresBase = nbHeuresBase;
	}
	
	public BigDecimal getTauxBase() {
		return tauxBase;
	}
	
	public void setTauxBase(BigDecimal tauxBase) {
		this.tauxBase = tauxBase;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPrixAnnuel(){
		return new PaieUtils().formaterBigDecimal(this.nbHeuresBase.multiply(this.tauxBase).multiply(new BigDecimal("12")));
	}

}
