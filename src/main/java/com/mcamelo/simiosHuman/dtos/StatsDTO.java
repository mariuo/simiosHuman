package com.mcamelo.simiosHuman.dtos;

public class StatsDTO {
	
	private Integer count_mutant_dna;
	private Integer count_human_dna;
	private Double score;
	
	public StatsDTO() {
		
	}

	public StatsDTO(Integer count_mutant_dna, Integer count_human_dna, Double score) {
		super();
		this.count_mutant_dna = count_mutant_dna;
		this.count_human_dna = count_human_dna;
		this.score = score;
	}

	public Integer getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(Integer count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	public Integer getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(Integer count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	public void calc(Integer sim, Integer hum) {
		this.score = (double) (sim/hum);
	}
	
}
