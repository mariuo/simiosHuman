package com.mcamelo.simiosHuman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcamelo.simiosHuman.entities.Dna;

@Repository
public interface DnaRepository extends JpaRepository<Dna, Long>{

}
