package com.example.springmodels.repository;

import com.example.springmodels.models.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedRepository extends JpaRepository<Bed, Long> {
    Bed findByNumberBed(String numberBed);
}
