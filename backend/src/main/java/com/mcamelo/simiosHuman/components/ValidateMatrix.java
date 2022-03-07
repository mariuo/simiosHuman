package com.mcamelo.simiosHuman.components;

import org.springframework.stereotype.Component;

import com.mcamelo.simiosHuman.dtos.DnaTestDTO;

@Component
public class ValidateMatrix {
	
	public String[][] convertMatrix(DnaTestDTO dto) {
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
	
	public boolean checkMatrixNN(DnaTestDTO dto) {		
		int lines = dto.getDna().size();
					
		for(String item : dto.getDna()) {
			if(item.length() != lines) {
				return false;
			}
		}
		return true;
	}
	
	public void view(String[][] matrix) {
		int lines = matrix.length;
		int col = lines;
		
		for(int x = 0; x <lines; x++) {
			for(int y = 0; y <col; y++) {
				System.out.print(matrix[x][y]+" ");
			}
			System.out.println("");
		}
	}
	
	public boolean horizontal(String[][] matrix) {
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
	
	public boolean vertical(String[][] matrix) {
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
	
	public boolean diagonal(String[][] matrix) {
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
