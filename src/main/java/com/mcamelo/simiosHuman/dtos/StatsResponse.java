package com.mcamelo.simiosHuman.dtos;

public record StatsResponse(
        Integer count_mutant_dna,
        Integer count_human_dna,
        Double score
) {
    public StatsResponse {
    }

    public StatsResponse calc(Integer sim, Integer hum) {
        return new StatsResponse(count_mutant_dna, count_human_dna, (double) sim / hum);
    }
}
