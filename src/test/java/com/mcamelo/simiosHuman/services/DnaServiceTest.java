package com.mcamelo.simiosHuman.services;

import com.mcamelo.simiosHuman.factory.DnaFactory;
import com.mcamelo.simiosHuman.repositories.DnaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.when;

@SpringBootTest

public class DnaServiceTest {

	@Autowired
	private DnaValidationService service;

	@MockBean
	private DnaRepository repo;
	
	private final String[] dnaSimian = {"CTGAGA", "CTATGA", "TATTGA", "AGAGGA", "CCCCTA", "TGAAAA"};
	private final String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG","GCGTCA" ,"GCGTCA"};
	private final String[] dnaNotValid = {"ATGCGA", "CAGTGC", "TTATTT"};
	private final char[][] dnaSimianChar = {
			{'C', 'T', 'G', 'A', 'G', 'A'},
			{'C', 'T', 'A', 'T', 'G', 'A'},
			{'T', 'A', 'T', 'T', 'G', 'A'},
			{'A', 'G', 'A', 'G', 'G', 'A'},
			{'C', 'C', 'C', 'C', 'T', 'A'},
			{'T', 'G', 'A', 'A', 'A', 'A'}
	};
	private final char[][] dnaHumanChar = {
			{'A', 'T', 'G', 'C', 'G', 'A'},
			{'C', 'A', 'G', 'T', 'G', 'C'},
			{'T', 'T', 'A', 'T', 'T', 'T'},
			{'A', 'G', 'A', 'C', 'G', 'G'},
			{'G', 'C', 'G', 'T', 'C', 'A'},
			{'G', 'C', 'G', 'T', 'C', 'A'}
	};
	private final char[][] dnaNotValidChar = {
			{'A', 'T', 'G', 'C', 'G', 'A'},
			{'C', 'A', 'G', 'T', 'G', 'C'},
			{'T', 'T', 'A', 'T', 'T', 'T'}
	};


	@BeforeEach
	void setUp() throws Exception{

	}
	@Test
	public void testConvertMatrixWithValidInput() {
		// Act
		char[][] actualMatrix = service.convertMatrix(dnaSimian);

		// Assert
		assertArrayEquals(dnaSimianChar, actualMatrix);
	}

	@Test
	public void testConvertMatrixWithLowercaseInput() {
		// Arrange
		String[] dnaArray = {
				"atgc",
				"cagt",
				"ttat",
				"agac"
		};

		char[][] expectedMatrix = {
				{'A', 'T', 'G', 'C'},
				{'C', 'A', 'G', 'T'},
				{'T', 'T', 'A', 'T'},
				{'A', 'G', 'A', 'C'}
		};

		// Act
		char[][] actualMatrix = service.convertMatrix(dnaArray);

		// Assert
		assertArrayEquals(expectedMatrix, actualMatrix);
	}

	@Test
	public void methodCheckMatrixShouldReturnTrueWhenValidData() throws Exception{
		boolean result = service.checkMatrixNN(dnaSimian);
		Assertions.assertEquals(true,result);
	}

	@Test
	public void methodCheckMatrixShouldReturnFalseWhenInvalidData() throws Exception{
		boolean result = service.checkMatrixNN(dnaNotValid);
		Assertions.assertEquals(false, result);
	}

	@Test
	public void methodCheckDnaMatrixShouldReturnTrueWhenSimios() throws Exception{
		boolean result = service.checkDnaMatrix(dnaSimianChar);
		Assertions.assertEquals(true, result);
	}

	@Test
	public void methodCheckDnaMatrixShouldReturnFalseWhenHuman() throws Exception{
		boolean result = service.checkDnaMatrix(dnaHumanChar);
		Assertions.assertEquals(false, result);
	}
	@Test
	public void isValidSimianShouldReturnTrueWhenSimian() throws Exception{
		when(repo.save(ArgumentMatchers.any())).thenReturn(DnaFactory.createDnaSimios());
		boolean result = service.isValidSimian(dnaSimian);
		Assertions.assertEquals(true, result);
	}
	@Test
	public void isValidSimianShouldReturnFalseWhenHuman() throws Exception{
		when(repo.save(ArgumentMatchers.any())).thenReturn(DnaFactory.createDnaHuman());
		boolean result = service.isValidSimian(dnaHuman);
		Assertions.assertEquals(false, result);
	}

//	@Test
//	public void isSimianShouldReturnFalseWhenDnaHuman() throws Exception {
//		boolean result = service.isSimian(dnaHuman);
//
//		Assertions.assertEquals(result, false);
//
//	}
//	@Test
//	public void isSimianShouldThrowExceptionWhenDnaInvalid() {
//		Assertions.assertThrows(InvalideMatrixException.class, () ->{
//			service.isSimian(dnaNotValid);
//		});
//	}
//
//	@Test
//	public void isSimianShouldSaveWhenDnaSimian() throws Exception {
//		service.isSimian(dnaSimian);
//
//		Dna result = repo.getById(2L);
//
//		Assertions.assertEquals(result.getDnaType(), DnaType.SIMIOS);
//
//	}
//	@Test
//	public void isSimianShouldSaveWhenDnaHuman() throws Exception {
//		service.isSimian(dnaHuman);
//
//		Dna result = repo.getById(3L);
//
//		Assertions.assertEquals(result.getDnaType(), DnaType.HUMAN);
//
//	}

}
