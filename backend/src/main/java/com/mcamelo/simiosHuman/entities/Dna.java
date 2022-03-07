package com.mcamelo.simiosHuman.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mcamelo.simiosHuman.entities.enums.DnaType;

@Entity
@Table(name = "tb_dna")
public class Dna implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TEXT")
	private String name;
	
	private DnaType dnaType;
	
	public Dna() {
		
	}

	
	public Dna(Long id, String name, DnaType dnaType) {
		super();
		this.id = id;
		this.name = name;
		this.dnaType = dnaType;
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


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dna other = (Dna) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
