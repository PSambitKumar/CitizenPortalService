package com.sambit.citizenportalservice.repository;

import com.sambit.citizenportalservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 31/12/2022 - 4:36 PM
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
