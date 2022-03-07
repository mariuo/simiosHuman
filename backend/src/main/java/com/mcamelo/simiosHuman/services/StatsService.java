package com.mcamelo.simiosHuman.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcamelo.simiosHuman.dtos.StatsDTO;
import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.entities.enums.DnaType;
import com.mcamelo.simiosHuman.repositories.DnaRepository;

@Service
public class StatsService {
	
	@Autowired
	private DnaRepository repository;
	
	
	@Transactional(readOnly = true)
	public StatsDTO result() {
		int sim = 0, hum=0;
		double score = 0.0;
		List<Dna> list = repository.findAll();
		if(list != null) {
			for(Dna x : list) {
				if(x.getDnaType() == DnaType.SIMIOS) {
					sim++;
					
				}else {
					hum++;
					
				}
			}
			if(hum != 0) {
				score = (double)sim/(double)hum;
				BigDecimal bd = new BigDecimal(score).setScale(1, RoundingMode.HALF_UP);
				score = (double) bd.doubleValue();
			}
		}		
		return new StatsDTO(sim,hum,score);
	}
		
	
}
