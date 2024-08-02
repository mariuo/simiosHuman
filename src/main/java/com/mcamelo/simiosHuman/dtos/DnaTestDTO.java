package com.mcamelo.simiosHuman.dtos;

import java.io.Serializable;

public class DnaTestDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String[] dna;
	
	public DnaTestDTO() {
		
	}

	public DnaTestDTO(String[] dna) {
		super();
		this.dna = dna;
	}

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

	
}
