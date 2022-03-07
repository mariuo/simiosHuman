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
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity isSimian(@RequestBody DnaTestDTO dto){
		boolean result = service.isSimian(dto.getDna());
		var resu = ResponseEntity.status(403).build();
		if(result) {
			resu = ResponseEntity.status(200).build();
		}else {
			resu = ResponseEntity.status(403).build();
		}
		return resu;
	}
	
	@GetMapping
	public ResponseEntity<List<DnaDTO>> findAll(){
		//List<CategoryDTO> listDto = service.findAll();
		List<DnaDTO> list = service.findAll();		
		return ResponseEntity.ok().body(list);
	}
	

}
