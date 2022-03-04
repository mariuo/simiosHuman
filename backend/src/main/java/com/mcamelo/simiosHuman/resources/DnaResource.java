package com.mcamelo.simiosHuman.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcamelo.simiosHuman.dtos.DnaDTO;
import com.mcamelo.simiosHuman.services.DnaService;

@RestController
@RequestMapping(value = "/simian")
public class DnaResource {
	
	@Autowired
	private DnaService service;
	
	@GetMapping
	public ResponseEntity<List<DnaDTO>> findAll(){
		//List<CategoryDTO> listDto = service.findAll();
		List<DnaDTO> list = service.findAll();		
		return ResponseEntity.ok().body(list);
	}
	

}
