package com.sambit.citizenportalservice.repository;

import com.sambit.citizenportalservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 02/01/2023 - 12:05 AM
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
}
