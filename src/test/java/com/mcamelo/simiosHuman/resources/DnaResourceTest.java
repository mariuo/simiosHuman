package com.mcamelo.simiosHuman.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcamelo.simiosHuman.dtos.DnaRequest;
import com.mcamelo.simiosHuman.services.DnaValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DnaResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DnaValidationService service;

	@Autowired
	private ObjectMapper objectMapper;
	
	private String[] dnaSimian = {"CTGAGA", "CTATGA", "TATTGA", "AGAGGA", "CCCCTA", "TGAAAA"};
	private String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG","GCGTCA","GCGTCA"};
	private String[] dnaNotValid = {"ATGCGA", "CAGTGC", "TTATTT"};


	@BeforeEach
	void setUp() throws Exception{

	}

	@Test
	public void isSimianCorrectDnashouldReturn200() throws Exception {
		//Arrange
		when(service.checkMatrixNN(dnaSimian)).thenReturn(Boolean.TRUE);
		when(service.isValidSimian(dnaSimian)).thenReturn(Boolean.TRUE);
		DnaRequest dto = new DnaRequest(dnaSimian);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		//Act
		ResultActions result = mockMvc.perform(post("/simian")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//Assert
		result.andExpect(status().isOk());
    }
	
	@Test
	public void isHumanCorrectDnaShouldReturn403() throws Exception {
		when(service.checkMatrixNN(dnaHuman)).thenReturn(Boolean.TRUE);
		when(service.isValidSimian(dnaHuman)).thenReturn(Boolean.FALSE);
		//Arrange		
		DnaRequest dto = new DnaRequest(dnaHuman);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		//Act
		ResultActions result = mockMvc.perform(post("/simian")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//Assert
		result.andExpect(status().isForbidden());		
    }
	@Test
	public void isSimianShouldReturn400WhenNotValidMatrix() throws Exception {
		when(service.checkMatrixNN(dnaNotValid)).thenReturn(Boolean.FALSE);
//		when(service.isValidSimian(dnaNotValid)).thenReturn(Boolean.FALSE);
		DnaRequest dto = new DnaRequest(dnaNotValid);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		//Act
		ResultActions result = mockMvc.perform(post("/simian")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//Assert
		result.andExpect(status().isBadRequest());		
	}
}
