package com.sambit.citizenportalservice.repository;

import com.sambit.citizenportalservice.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {
    @Query("SELECT DISTINCT s.StateName, s.stateId FROM State s ORDER BY s.StateName")
    List<Object[]> findAllStateName();
    State findStateByStateId(Long stateId);
}