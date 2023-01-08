package com.sambit.citizenportalservice.repository;

import com.sambit.citizenportalservice.model.CitiesMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesMasterRepository extends JpaRepository<CitiesMaster, Integer> {
}