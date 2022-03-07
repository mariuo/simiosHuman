package com.mcamelo.simiosHuman.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcamelo.simiosHuman.dtos.StatsDTO;
import com.mcamelo.simiosHuman.services.StatsService;

@RestController
@RequestMapping(value = "/stats")
public class StatsResource {
	
	@Autowired
	private StatsService service;
	
	@GetMapping
	public ResponseEntity<StatsDTO> result(){
		//List<CategoryDTO> listDto = service.findAll();
		StatsDTO dto = service.result();		
		return ResponseEntity.ok().body(dto);
	}
}
