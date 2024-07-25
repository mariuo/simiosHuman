package com.mcamelo.simiosHuman.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mcamelo.simiosHuman.dtos.StatsDTO;

@SpringBootTest
@Transactional
public class StatsServiceTest {
	
	@Autowired
	private StatsService service;
	
	@BeforeEach
	void setUp() throws Exception{
		
	}
	
	@Test
	public void getStatsShouldReturnStatsDTO() throws Exception {
		
		StatsDTO result = service.getStats();
		
		Assertions.assertNotNull(result);
	}
	
	
	

}
