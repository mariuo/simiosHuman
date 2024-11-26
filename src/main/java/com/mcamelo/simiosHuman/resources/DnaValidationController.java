package com.mcamelo.simiosHuman.resources;

import com.mcamelo.simiosHuman.services.DnaValidationService;
import com.mcamelo.simiosHuman.dtos.DnaRequest;
import com.mcamelo.simiosHuman.dtos.DnaResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simian")
public class DnaValidationController implements DnaValidation {

    @Autowired
    private DnaValidationService dnaValidationService;

    @PostMapping
    public ResponseEntity<Void> validateDna(@Valid @RequestBody DnaRequest dnaRequest) {
        if (dnaRequest == null || dnaRequest.dna() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (!dnaValidationService.isMatrixNN(dnaRequest.dna())) {
            return ResponseEntity.badRequest().build();
        }
        return dnaValidationService.isSimian(dnaRequest.dna()) ? ResponseEntity.ok().build() : ResponseEntity.status(403).build();
    }
    @GetMapping
    public ResponseEntity<List<DnaResponse>> findAll(){
        List<DnaResponse> list = dnaValidationService.findAll();
        return ResponseEntity.ok().body(list);
    }
}