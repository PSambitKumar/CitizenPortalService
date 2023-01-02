package com.sambit.citizenportalservice.repository;

import com.sambit.citizenportalservice.model.URLGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface URLGroupRepository extends JpaRepository<URLGroup, Long> {
}