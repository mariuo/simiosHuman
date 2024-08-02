package com.mcamelo.simiosHuman.factory;

import com.mcamelo.simiosHuman.dtos.DnaDTO;
import com.mcamelo.simiosHuman.dtos.DnaTestDTO;
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
    public static DnaTestDTO createDnaDtoTestSimios(){
        String[] dnaString = {"CTGAGA", "CTATGA", "TATTGA", "AGAGGA", "CCCCTA", "TGAAAA"};
        return new DnaTestDTO(dnaString);
    }
    public static DnaTestDTO createDnaDtoTestHuman(){
        String[] dnaString = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG","GCGTCA" ,"GCGTCA"};
        return new DnaTestDTO(dnaString);
    }
    public static DnaDTO createDnaDTOSimios(){
        return new DnaDTO(createDnaSimios());
    }

    public static DnaDTO createDnaDTOHuman(){
        return new DnaDTO(createDnaHuman());
    }

}
