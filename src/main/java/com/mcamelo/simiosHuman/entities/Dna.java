package com.mcamelo.simiosHuman.entities;

import com.mcamelo.simiosHuman.entities.enums.DnaType;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_dna")
public class Dna implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TEXT")
	private String sequence;
	
	private DnaType dnaType;
	
	public Dna() {
		
	}

	public Dna(Long id, String sequence, DnaType dnaType) {
		super();
		this.id = id;
		this.sequence =sequence;
		this.dnaType = dnaType;
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
