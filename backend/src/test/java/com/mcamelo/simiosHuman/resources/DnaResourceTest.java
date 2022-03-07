package com.mcamelo.simiosHuman.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcamelo.simiosHuman.dtos.DnaTestDTO;
import com.mcamelo.simiosHuman.services.DnaService;

@SpringBootTest

@AutoConfigureMockMvc
public class DnaResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private DnaService service;
	@InjectMocks
	private DnaResource resource;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private String[] dnaSimian = {"CTGAGA", "CTATGA", "TATTGA", "AGAGGA", "CCCCTA", "TGAAAA"};
	private String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG","GCGTCA" ,"GCGTCA"};
	
	@BeforeEach
	void setUp() throws Exception{
		
		when(service.isSimian(dnaSimian)).thenReturn(Boolean.TRUE);
		when(service.isSimian(dnaHuman)).thenReturn(Boolean.FALSE);
	}

	@Test
	public void isSimianCorrectDnashouldReturn200() throws Exception {
		
		
		DnaTestDTO dto = new DnaTestDTO(dnaSimian);
		
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result = mockMvc.perform(post("/simian")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		
    }
	@Test
	public void isHumanCorrectDnashouldReturn403() throws Exception {
				
		DnaTestDTO dto = new DnaTestDTO(dnaHuman);
		
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result = mockMvc.perform(post("/simian")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isForbidden());
		
    }
}
