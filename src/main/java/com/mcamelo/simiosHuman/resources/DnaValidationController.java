package com.mcamelo.simiosHuman.resources;

import com.mcamelo.simiosHuman.components.DnaValidationService;
import com.mcamelo.simiosHuman.dtos.DnaDTO;
import com.mcamelo.simiosHuman.dtos.DnaRequest;
import com.mcamelo.simiosHuman.dtos.DnaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dna")
public class DnaValidationController {

    @Autowired
    private DnaValidationService dnaValidationService;

    @PostMapping("/validate")
    public ResponseEntity<String> validateDna(@RequestBody DnaRequest dnaRequest) {
        if (!dnaValidationService.checkMatrixNN(dnaRequest.dna())) {
            return ResponseEntity.badRequest().build();
        }
        if(dnaValidationService.isValidSimian(dnaRequest.dna())){
            return ResponseEntity.ok().build();

        }else{
            return ResponseEntity.badRequest().build();

        }
    }
    @GetMapping
    public ResponseEntity<List<DnaResponse>> findAll(){
        List<DnaResponse> list = dnaValidationService.findAll();
        return ResponseEntity.ok().body(list);
    }
}