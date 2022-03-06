package com.mcamelo.simiosHuman.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcamelo.simiosHuman.dtos.DnaDTO;
import com.mcamelo.simiosHuman.dtos.DnaTestDTO;
import com.mcamelo.simiosHuman.dtos.StatsDTO;
import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.repositories.CategoryRepository;
import com.mcamelo.simiosHuman.repositories.DnaRepository;
import com.mcamelo.simiosHuman.services.exceptions.ResourceNotFoundException;

@Service
public class DnaService {
	
	@Autowired
	private DnaRepository repository;
	
	@Autowired
	private CategoryRepository catRepository;
	
	@Transactional(readOnly = true)
	public List<DnaDTO> findAll(){
		List<Dna> list = repository.findAll();		
		
		return list.stream().map(x -> new DnaDTO(x)).collect(Collectors.toList());
	}
	@Transactional(readOnly = true)
	public StatsDTO result() {
		int sim = 0, hum=0;
		List<Dna> list = repository.findAll();
		for(Dna x : list) {
			if(x.getCategory().getId() == 2L) {
				sim++;
				
			}else {
				hum++;
				
			}
		}
		Double score = (double)sim/(double)hum;
		BigDecimal bd = new BigDecimal(score).setScale(1, RoundingMode.HALF_UP);
		score = (double) bd.doubleValue();
		System.out.println(score);
		return new StatsDTO(sim,hum,score);
	}
	@Transactional
	public DnaDTO isSimian(DnaTestDTO dto) {
		
		if(!checkMatrixNN(dto)) {
			new ResourceNotFoundException("Entity not found");
		}
		
		//checkMatrixNN(dto);
		if(convertMatrix(dto) != null) {
			String[][] mat = convertMatrix(dto);
		
			if (horizontal(mat) || vertical(mat) || diagonal(mat)) {
			// Insert DNA in database as Simios Category=2
				Dna entity = new Dna();
				entity.setName(dto.getDna().toString());
				entity.setCategory(catRepository.findById(2L).get());
				entity = repository.save(entity);
			
				return new DnaDTO(entity);
					
			}else {
			// Insert DNA in database as Human Category=1
						Dna entity = new Dna();
						entity.setName(dto.getDna().toString());
						entity.setCategory(catRepository.findById(1L).get());
						entity = repository.save(entity);
						
				return new DnaDTO(entity);
			}
		}else {
			return null;
		}
	}
	
	public static String[][] convertMatrix(DnaTestDTO dto) {
		int lines = dto.getDna().size();
		int col = dto.getDna().get(0).length();
		
		if(checkMatrixNN(dto)) {			
			String[][] matrizDna = new String[lines] [col];
			
			for(int x = 0; x<lines; x++) {
				for(int y = 0; y<col; y++) {
					matrizDna[x][y] = (dto.getDna().get(x).charAt(y)+"").toUpperCase();
				}
			}
			//view(matrizDna);
			return matrizDna;
			
		}else {
			System.out.println("NAO É NXN");
			return null;
			}
		
	}
	
	public static boolean checkMatrixNN(DnaTestDTO dto) {		
		int lines = dto.getDna().size();
					
		for(String item : dto.getDna()) {
			if(item.length() != lines) {
				return false;
			}
		}
		return true;
	}
	
	public static void view(String[][] matrix) {
		int lines = matrix.length;
		int col = lines;
		
		for(int x = 0; x <lines; x++) {
			for(int y = 0; y <col; y++) {
				System.out.print(matrix[x][y]+" ");
			}
			System.out.println("");
		}
	}
	public static boolean horizontal(String[][] matrix) {
		int lines = matrix.length;
		int col = lines;
		String temp;
		//String achou;
		boolean result = false;
		int y=0, x=0, qnt=0, count=1;
		for(x=0 ;  x<lines ; x++) {
			temp = matrix[x][y];
			for(y = 1 ; y < col ; y++) {
				if(temp.intern() == matrix[x][y].intern()) {
					count++;
					if (count >= 4) {
						qnt++;
						break;
					}
				}else {
					count = 1;
					temp = matrix[x][y];					
				}				
			}
			y=0;
		}
		if (qnt!=0) {
			result = true;
			//achou= "FOUNDED! "+qnt;
			count=1;
			
		}else {
			//achou= "NOT FOUND!";
			count=1;
			result = false;
		}
		return result;		
	}
	public static boolean vertical(String[][] matrix) {
		int lines = matrix.length;
		int col = lines;
		String temp;
		//String achou;
		boolean result;
		int c=0, l=0, qnt=0, count=1, x=0,y=0;
			
		
		for(l=0 ;  l <= lines-4 ; l++) {
			
			temp = matrix[l][c];
			
			for(c = 0 ; c < col ; c++) {
				
				temp = matrix[l][c];
				x=l;
				y=c;
				count=1;
					 while(temp.intern() == matrix[x+1][y].intern() ) {
						 
						count++;
						
						if (count == 4) {
							qnt++;
							count=1;
							break;
						}
						temp = matrix[x+1][y];
						x++;
					}
					
			}
			c=0;
				
		}
		
		if (qnt!=0) {
			//achou= "FOUNDED! "+ qnt;
			result = true;
			count=1;
			
		}else {
			//achou= "NOT FOUND!";
			result = false;
			count=1;
		}
		return result;		
	
	}
	
	public static boolean diagonal(String[][] matrix) {
		int lines = matrix.length;
		int col = lines;
		String temp;
		//String achou;
		boolean result;
		int c=0, l=0, qnt=0, count=1, x=0,y=0;
		// TOP-DOWN LEFT->RIGHT
		for(l=0 ;  l < lines-4 ; l++) {
			
			temp = matrix[l][c];
			
			for(c = 3 ; c < col ; c++) {
				
				temp = matrix[l][c];
				x=l;
				y=c;
				count=1;
					 while(temp.intern() == matrix[x+1][y-1].intern() ) {
						 
						count++;
						
						if (count >= 4) {
							qnt++;
							count=1;
							break;
						}
						
						temp = matrix[x+1][y-1];
						x++;
						y--;
						
					}
					
			}
			c=3;				
		}
		
		// TOP-DOWN RIGHT->LEFT
		for(l=0 ;  l<lines-1 ; l++) {
			
			temp = matrix[l][c];
			
			for(c = 0 ; c < col-1 ; c++) {
				
				temp = matrix[l][c];
				x=l;
				y=c;
				count=1;
					 while(temp.intern() == matrix[x+1][y+1].intern() && (y <= c-1) ) {
						 
						count++;
						
						if (count >= 4) {
							qnt++;
							count=1;
							break;
						}
						temp = matrix[x+1][y+1];
						x++;
						y++;
					}
					
				}
				
		}
		
		if (qnt!=0) {
			//achou= "FOUNDED! "+ qnt;
			result = true;
			count=1;
			
		}else {
			//achou= "NOT FOUND!";
			result = false;
			count=1;
		}
		return result;		
	
	}
	
	
}
