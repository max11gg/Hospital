package com.example.springmodels.repository;

import com.example.springmodels.models.Laboratory;
import com.example.springmodels.models.Palat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
    Laboratory findByNameLaboratory(String nameLaboratory);
}
