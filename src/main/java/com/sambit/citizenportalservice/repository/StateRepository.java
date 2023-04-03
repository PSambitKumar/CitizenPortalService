package com.sambit.citizenportalservice.repository;

import com.sambit.citizenportalservice.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT DISTINCT s.StateName, s.stateId FROM State s ORDER BY s.StateName")
    List<Object[]> findAllStateName();
    State findStateByStateId(Long stateId);

    @Query("SELECT s FROM State s WHERE s.country.countryId = ?1 ORDER BY s.StateName")
    List<State> findAllStatesByCountryId(Long countryId);

    State getStateByStateId(Long stateId );
}