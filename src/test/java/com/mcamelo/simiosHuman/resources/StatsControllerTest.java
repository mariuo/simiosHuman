package com.mcamelo.simiosHuman.resources;

import com.mcamelo.simiosHuman.factory.DnaFactory;
import com.mcamelo.simiosHuman.services.StatsService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StatsService service;

	@BeforeEach
	void setUp() throws Exception{
		when(service.getStats()).thenReturn(DnaFactory.createStatsResponse());
	}

	@Test
	public void getStatsShouldReturnStatus200() throws Exception {
		//Act
		ResultActions result = mockMvc.perform(get("/stats")
					.accept(MediaType.APPLICATION_JSON));
		//Assert
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.count_mutant_dna").exists());
		result.andExpect(jsonPath("$.count_human_dna").exists());
		result.andExpect(jsonPath("$.score").exists());
		
    }
	
}
