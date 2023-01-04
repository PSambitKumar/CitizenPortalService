package com.sambit.citizenportalservice.repository;

import com.sambit.citizenportalservice.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}