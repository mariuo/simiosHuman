package com.mcamelo.simiosHuman.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.entities.enums.DnaType;
import com.mcamelo.simiosHuman.repositories.DnaRepository;
import com.mcamelo.simiosHuman.services.exceptions.InvalideMatrixException;

@SpringBootTest
@Transactional
public class DnaServiceTest {
	
	@Autowired
	private DnaService service;
	
	@Autowired
	private DnaRepository repo;
	
	private String[] dnaSimian = {"CTGAGA", "CTATGA", "TATTGA", "AGAGGA", "CCCCTA", "TGAAAA"};
	private String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG","GCGTCA" ,"GCGTCA"};
	private String[] dnaNotValid = {"ATGCGA", "CAGTGC", "TTATTT"};
	
	@BeforeEach
	void setUp() throws Exception{
		
	}
	
	@Test
	public void isSimianShouldReturnTrueWhenDnaSimian() throws Exception {
		boolean result = service.isSimian(dnaSimian);
		
		Assertions.assertEquals(result, true);
		
	}
	@Test
	public void isSimianShouldReturnFalseWhenDnaHuman() throws Exception {
		boolean result = service.isSimian(dnaHuman);
		
		Assertions.assertEquals(result, false);
		
	}
	@Test
	public void isSimianShouldThrowExceptionWhenDnaInvalid() {
		Assertions.assertThrows(InvalideMatrixException.class, () ->{
			service.isSimian(dnaNotValid);
		});
	}
	
	@Test
	public void isSimianShouldSaveWhenDnaSimian() throws Exception {
		service.isSimian(dnaSimian);		
		
		Dna result = repo.getById(2L);
		
		Assertions.assertEquals(result.getDnaType(), DnaType.SIMIOS);
		
	}
	@Test
	public void isSimianShouldSaveWhenDnaHuman() throws Exception {
		service.isSimian(dnaHuman);		
		
		Dna result = repo.getById(3L);
		
		Assertions.assertEquals(result.getDnaType(), DnaType.HUMAN);
		
	}

}
