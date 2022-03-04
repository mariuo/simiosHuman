package com.mcamelo.simiosHuman.dtos;

import java.io.Serializable;

import com.mcamelo.simiosHuman.entities.Dna;

public class DnaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	private String categoryName;
	
	public DnaDTO() {
		
	}
	public DnaDTO(Long id, String name, String categoryName) {
		this.id = id;
		this.name = name;
		this.categoryName = categoryName;
	}
	public DnaDTO(Dna entity) {
		id = entity.getId();
		name = entity.getName();
		categoryName = entity.getCategory().getName();
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
