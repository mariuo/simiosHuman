package com.mcamelo.simiosHuman.factory;

import com.mcamelo.simiosHuman.dtos.StatsResponse;
import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.entities.enums.DnaType;

public class DnaFactory {
    public static Dna createDnaSimios(){
        return new Dna(1L, "CTGAGA CTATGA TATTGA AGAGGA CCCCTA TGAAAA", DnaType.SIMIOS);
    }
    public static Dna createDnaHuman(){
        return new Dna(1L, "ATGCGA CAGTGC TTATTT AGACGG GCGTCA GCGTCA", DnaType.HUMAN);
    }
    public static Dna createDna(){
        return new Dna();
    }

    public static StatsResponse createStatsResponse() {return new StatsResponse(0,0,0.0);}

}
