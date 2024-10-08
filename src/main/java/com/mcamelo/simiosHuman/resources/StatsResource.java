package com.mcamelo.simiosHuman.resources;

import com.mcamelo.simiosHuman.dtos.StatsResponse;
import com.mcamelo.simiosHuman.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stats")
public class StatsResource {
	
	@Autowired
	private StatsService service;
	
	@GetMapping
	public ResponseEntity<StatsResponse> getStats(){
		StatsResponse response = service.getStats();
		return ResponseEntity.ok().body(response);
	}
}
