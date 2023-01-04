package com.sambit.citizenportalservice.repository;

import com.sambit.citizenportalservice.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
}