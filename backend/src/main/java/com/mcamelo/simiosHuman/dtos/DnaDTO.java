package com.mcamelo.simiosHuman.dtos;

import java.io.Serializable;

import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.entities.enums.DnaType;

public class DnaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	private DnaType dnaType;
	
	public DnaDTO() {
		
	}
	public DnaDTO(Long id, String name, DnaType dnaType) {
		this.id = id;
		this.name = name;
		this.dnaType = dnaType;
	}
	public DnaDTO(Dna entity) {
		id = entity.getId();
		name = entity.getName();
		dnaType = entity.getDnaType();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DnaType getDnaType() {
		return dnaType;
	}
	public void setDnaType(DnaType dnaType) {
		this.dnaType = dnaType;
	}
	
	
}
