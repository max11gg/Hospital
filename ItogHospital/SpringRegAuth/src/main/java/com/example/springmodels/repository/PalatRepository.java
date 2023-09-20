package com.example.springmodels.repository;

import com.example.springmodels.models.Palat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalatRepository extends JpaRepository<Palat, Long> {
    Palat findByNumberPalat(String numberPalat);
}
