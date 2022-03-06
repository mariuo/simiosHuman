package com.mcamelo.simiosHuman.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcamelo.simiosHuman.dtos.DnaDTO;
import com.mcamelo.simiosHuman.dtos.DnaTestDTO;
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
	
	/*
	@PostMapping
	public ResponseEntity<DnaDTO> insert(@RequestBody DnaTestDTO dto){
		dto = service.tryInsert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	*/
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<DnaDTO> isSimian(@RequestBody DnaTestDTO dto){
		DnaDTO result = service.isSimian(dto);
		var resu = ResponseEntity.status(403).body(result);
		if(result != null) {
			if(result.getCategoryName().contains("Simios"))
				resu = ResponseEntity.status(200).body(result);
			else {
				resu = ResponseEntity.status(403).body(result);
			}
		 
		}
		return resu;
	}
	

}
