package com.mcamelo.simiosHuman.dtos;

import com.mcamelo.simiosHuman.entities.enums.DnaType;

public record DnaResponse(Long id, String sequence, DnaType dnaType) {
}
