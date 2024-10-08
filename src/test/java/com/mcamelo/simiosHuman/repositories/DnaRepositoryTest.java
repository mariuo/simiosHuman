package com.mcamelo.simiosHuman.repositories;

import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.entities.enums.DnaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
public class DnaRepositoryTest {

    @Autowired
    public DnaRepository dnaRepository;

    @Test
    public void ShouldBeSomeData() throws Exception{
        var olog = dnaRepository.save(new Dna(1L,"\"CTGAGA\", \"CTATGA\", \"TATTGA\", \"AGAGGA\", \"CCCCTA\", \"TGAAAA\"", DnaType.SIMIOS));
        var all = dnaRepository.findAll();

        Assertions.assertTrue(all.iterator().hasNext(), "There should be some data");
    }

    @Test
    public void ShouldBeNoData() throws Exception{
        Assertions.assertFalse(dnaRepository.findAll().iterator().hasNext(), () -> "There should be no data");
    }
}
