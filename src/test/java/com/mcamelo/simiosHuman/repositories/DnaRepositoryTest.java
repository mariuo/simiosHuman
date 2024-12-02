package com.mcamelo.simiosHuman.repositories;

import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.entities.enums.DnaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

@Testcontainers
@SpringBootTest
public class DnaRepositoryTest {
    @Autowired
    public DnaRepository dnaRepository;


    @Test
    public void ShouldBeSomeData() throws Exception{
        Dna newDna = new Dna();
        newDna.setSequence("\"CTGAGA\", \"CTATGA\", \"TATTGA\", \"AGAGGA\", \"CCCCTA\", \"TGAAAA\"");
        newDna.setDnaType(DnaType.SIMIOS);
        Dna olog = dnaRepository.save(newDna);
        Optional<Dna> all = dnaRepository.findById(1L);

        Assertions.assertEquals(all.stream().findAny().get().getId(), olog.getId());
    }

    @Test
    public void ShouldBeNoData() throws Exception{
        Assertions.assertFalse(dnaRepository.findAll().iterator().hasNext(), () -> "There should be no data");
    }


}
