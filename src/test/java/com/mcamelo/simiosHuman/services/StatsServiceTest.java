package com.mcamelo.simiosHuman.services;

import com.mcamelo.simiosHuman.dtos.StatsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatsServiceTest {
	
	@Autowired
	private StatsService service;
	
	@BeforeEach
	void setUp() throws Exception{
		
	}
	
	@Test
	public void getStatsShouldReturnStatsDTO() throws Exception {
		
		StatsResponse result = service.getStats();
		
		Assertions.assertNotNull(result);
	}
	
	
	

}
