package com.mcamelo.simiosHuman.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DnaTestDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<String> dna = new ArrayList<>();
	
	public DnaTestDTO() {
		
	}

	public DnaTestDTO(List<String> dna) {
		super();
		this.dna = dna;
	}

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}
	
	
	
	
	
}
