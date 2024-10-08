package com.mcamelo.simiosHuman.services;

import com.mcamelo.simiosHuman.components.ValidateMatrix;
import com.mcamelo.simiosHuman.dtos.DnaDTO;
import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.entities.enums.DnaType;
import com.mcamelo.simiosHuman.repositories.DnaRepository;
import com.mcamelo.simiosHuman.services.exceptions.InvalideMatrixException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DnaService {
	
	@Autowired
	private DnaRepository repository;
	
	@Autowired
	private ValidateMatrix validate;
	
	@Transactional
	public boolean isSimian(String[] dnaArray) {
		
		if(!validate.checkMatrixNN(dnaArray)) {
			throw new InvalideMatrixException("Matrix Invalide!");
		}
		
		if(validate.convertMatrix(dnaArray) != null) {
			String[][] mat = validate.convertMatrix(dnaArray);
		
			if (validate.horizontal(mat) || validate.vertical(mat) || validate.diagonal(mat)) {
			// Insert DNA in database as Simios
				Dna entity = new Dna();
				entity.setSequence(validate.convertToString(mat));
				entity.setDnaType(DnaType.SIMIOS);
				entity = repository.save(entity);
			
				return true;
					
			}
			else {
			// Insert DNA in database as Human Category=1
						Dna entity = new Dna();
						entity.setSequence(validate.convertToString(mat));
						entity.setDnaType(DnaType.HUMAN);
						entity = repository.save(entity);
						
				return false;
			}
		}else {
			return false;
		}
	}	
	@Transactional(readOnly = true)
	public List<DnaDTO> findAll(){
		List<Dna> list = repository.findAll();		
		return list.stream().map(x -> new DnaDTO(x)).collect(Collectors.toList());
	}
}
