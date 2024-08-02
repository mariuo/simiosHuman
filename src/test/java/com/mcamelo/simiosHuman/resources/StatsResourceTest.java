package com.mcamelo.simiosHuman.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mcamelo.simiosHuman.factory.DnaFactory;
import com.mcamelo.simiosHuman.repositories.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
public class StatsResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private DnaService service;

	@Mock
	private DnaRepository repository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private String[] dnaSimian = {"CTGAGA", "CTATGA", "TATTGA", "AGAGGA", "CCCCTA", "TGAAAA"};
	private String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG","GCGTCA" ,"GCGTCA"};
	private Integer dnaSimianCount = 1;
	private Integer dnaHumanCount = 1;
	private Integer dnaScoreCount = 1;
	
	@BeforeEach
	void setUp() throws Exception{
		when(service.isSimian(dnaSimian)).thenReturn(Boolean.TRUE);
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(DnaFactory.createDnaSimios());
		when(service.isSimian(dnaHuman)).thenReturn(Boolean.FALSE);

		//when(service.isSimian(dnaNotValid)).thenReturn(Boolean.FALSE);
	}

	@Test
	public void getStatsShouldReturnStatus200WhenNoData() throws Exception {
		//Act
		ResultActions result = mockMvc.perform(get("/stats")
					.accept(MediaType.APPLICATION_JSON));
		//Assert
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.count_mutant_dna").exists());
		result.andExpect(jsonPath("$.count_human_dna").exists());
		result.andExpect(jsonPath("$.score").exists());
		
    }
	
	@Test
	public void getStatsShouldReturnStatus200WhenDataFull() throws Exception {
		//Arrange
				DnaTestDTO dto = new DnaTestDTO(dnaSimian);
				DnaTestDTO dto2 = new DnaTestDTO(dnaHuman);
				String jsonBody = objectMapper.writeValueAsString(dto);
				String jsonBody2 = objectMapper.writeValueAsString(dto2);
				//Act
				mockMvc.perform(post("/simian").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
				mockMvc.perform(post("/simian").content(jsonBody2).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
				
				ResultActions result = mockMvc.perform(get("/stats")
						.accept(MediaType.APPLICATION_JSON));
				//Assert
				result.andExpect(status().isOk());	
				result.andExpect(jsonPath("$.count_mutant_dna").value(dnaSimianCount));
				result.andExpect(jsonPath("$.count_human_dna").value(dnaHumanCount));
				result.andExpect(jsonPath("$.score").value(dnaScoreCount));
	}
	
	
}
