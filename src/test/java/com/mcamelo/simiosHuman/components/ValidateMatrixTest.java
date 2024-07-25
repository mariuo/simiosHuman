package com.mcamelo.simiosHuman.components;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ValidateMatrixTest {

	
	@Autowired
	private ValidateMatrix validate;
	
	private String[] dnaSimian = {"CTGAGA", "CTATGA", "TATTGA", "AGAGGA", "CCCCTA", "TGAAAA"};
	private String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG","GCGTCA" ,"GCGTCA"};
	private String[] dnaNotValid = {"ATGCGA", "CAGTGC", "TTATTT"};
	
	private String[] dnaSimian2 = {"AGC", "ACG", "ACT"};
	private String[][] matrixSimian2 = { {"A", "G", "C"}, {"A", "C", "G"}, {"A", "C", "T"} };
	
	@BeforeEach
    public void setUp() throws Exception {
    }
	
	
	@Test
	public void checkMatrixShouldReturnTrueWhenMatrixisCorrect() {
		boolean expect = true;
	
		boolean result = validate.checkMatrixNN(dnaHuman);
	
		Assertions.assertEquals(expect, result);
	}
	@Test
	public void checkMatrixShouldReturnFalseWhenNotValideMatrix() {
		boolean expect = false;
	
		boolean result = validate.checkMatrixNN(dnaNotValid);
	
		Assertions.assertEquals(expect, result);
	}
	@Test
	public void horizontalShouldReturnTrueWhenDnaSimian() {
		
		boolean expect = true;
		
		boolean result = validate.horizontal(validate.convertMatrix(dnaSimian));
		
		Assertions.assertEquals(expect, result);
	}
	@Test
	public void horizontalShouldReturnFalseWhenDnaHuman() {
		
		boolean expect = false;
		
		boolean result = validate.horizontal(validate.convertMatrix(dnaHuman));
		
		Assertions.assertEquals(expect, result);
	}
	
	@Test
	public void verticalShouldReturnTrueWhenDnaSimian() {
		
		boolean expect = true;
		
		boolean result = validate.horizontal(validate.convertMatrix(dnaSimian));
		
		Assertions.assertEquals(expect, result);
	}
	@Test
	public void verticalShouldReturnFalseWhenDnaHuman() {
		
		boolean expect = false;
		
		boolean result = validate.horizontal(validate.convertMatrix(dnaHuman));
		
		Assertions.assertEquals(expect, result);
	}
	@Test
	public void diagonalShouldReturnTrueWhenDnaSimian() {
		
		boolean expect = true;
		
		boolean result = validate.horizontal(validate.convertMatrix(dnaSimian));
		
		Assertions.assertEquals(expect, result);
	}
	@Test
	public void diagonalShouldReturnFalseWhenDnaHuman() {
		
		boolean expect = false;
		
		boolean result = validate.horizontal(validate.convertMatrix(dnaHuman));
		
		Assertions.assertEquals(expect, result);
	}
	@Test
	public void convertMatrixShouldReturnMatrixBidimentional() {	
		
		String[][] result = validate.convertMatrix(dnaSimian2);
		
		Assertions.assertArrayEquals(matrixSimian2, result);
	}
	 
	
}
