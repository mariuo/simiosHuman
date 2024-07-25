package com.mcamelo.simiosHuman.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcamelo.simiosHuman.dtos.DnaTestDTO;
import com.mcamelo.simiosHuman.services.DnaService;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class DnaResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private DnaService service;
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private String[] dnaSimian = {"CTGAGA", "CTATGA", "TATTGA", "AGAGGA", "CCCCTA", "TGAAAA"};
	private String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG","GCGTCA" ,"GCGTCA"};
	private String[] dnaNotValid = {"ATGCGA", "CAGTGC", "TTATTT"};
	@BeforeEach
	void setUp() throws Exception{
		//when(service.isSimian(dnaSimian)).thenReturn(Boolean.TRUE);
		//when(service.isSimian(dnaHuman)).thenReturn(Boolean.FALSE);
		//when(service.isSimian(dnaNotValid)).thenReturn(Boolean.FALSE);
	}

	@Test
	public void isSimianCorrectDnashouldReturn200() throws Exception {
		//Arrange
		DnaTestDTO dto = new DnaTestDTO(dnaSimian);
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
	public void isHumanCorrectDnashouldReturn403() throws Exception {
		//Arrange		
		DnaTestDTO dto = new DnaTestDTO(dnaHuman);
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
		DnaTestDTO dto = new DnaTestDTO(dnaNotValid);
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
