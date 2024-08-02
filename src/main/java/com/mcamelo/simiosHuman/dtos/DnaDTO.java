package com.mcamelo.simiosHuman.dtos;

import java.io.Serializable;

import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.entities.enums.DnaType;

public class DnaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String sequence;
	private DnaType dnaType;
	public DnaDTO() {}
	public DnaDTO(Long id, String sequence, DnaType dnaType) {
		this.id = id;
		this.sequence = sequence;
		this.dnaType = dnaType;
	}
	public DnaDTO(Dna entity) {
		id = entity.getId();
		sequence = entity.getSequence();
		dnaType = entity.getDnaType();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public DnaType getDnaType() {
		return dnaType;
	}
	public void setDnaType(DnaType dnaType) {
		this.dnaType = dnaType;
	}
	
	
}
