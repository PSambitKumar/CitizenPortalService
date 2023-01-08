package com.sambit.citizenportalservice.repository;

import com.sambit.citizenportalservice.model.CountriesMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesMasterRepository extends JpaRepository<CountriesMaster, Integer> {
}