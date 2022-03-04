package com.mcamelo.simiosHuman.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcamelo.simiosHuman.dtos.DnaDTO;
import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.repositories.DnaRepository;

@Service
public class DnaService {
	
	@Autowired
	private DnaRepository repository;
	
	@Transactional(readOnly = true)
	public List<DnaDTO> findAll(){
		List<Dna> list = repository.findAll();		
		
		return list.stream().map(x -> new DnaDTO(x)).collect(Collectors.toList());
	}
	
}
