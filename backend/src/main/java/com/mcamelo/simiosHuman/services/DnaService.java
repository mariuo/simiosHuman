package com.mcamelo.simiosHuman.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcamelo.simiosHuman.components.ValidateMatrix;
import com.mcamelo.simiosHuman.dtos.DnaDTO;
import com.mcamelo.simiosHuman.dtos.DnaTestDTO;
import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.entities.enums.DnaType;
import com.mcamelo.simiosHuman.repositories.DnaRepository;
import com.mcamelo.simiosHuman.services.exceptions.ResourceNotFoundException;

@Service
public class DnaService {
	
	@Autowired
	private DnaRepository repository;
	
	@Autowired
	private ValidateMatrix validate;
	
	@Transactional(readOnly = true)
	public List<DnaDTO> findAll(){
		List<Dna> list = repository.findAll();		
		
		return list.stream().map(x -> new DnaDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public DnaDTO isSimian(DnaTestDTO dto) {
		
		if(!validate.checkMatrixNN(dto)) {
			new ResourceNotFoundException("Entity not found");
		}
		
		//checkMatrixNN(dto);
		if(validate.convertMatrix(dto) != null) {
			String[][] mat = validate.convertMatrix(dto);
		
			if (validate.horizontal(mat) || validate.vertical(mat) || validate.diagonal(mat)) {
			// Insert DNA in database as Simios
				Dna entity = new Dna();
				entity.setName(dto.getDna().toString());
				entity.setDnaType(DnaType.SIMIOS);
				entity = repository.save(entity);
			
				return new DnaDTO(entity);
					
			}else {
			// Insert DNA in database as Human Category=1
						Dna entity = new Dna();
						entity.setName(dto.getDna().toString());
						entity.setDnaType(DnaType.HUMAN);
						entity = repository.save(entity);
						
				return new DnaDTO(entity);
			}
		}else {
			return null;
		}
	}	
	
}
