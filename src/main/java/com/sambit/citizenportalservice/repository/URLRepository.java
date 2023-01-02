package com.sambit.citizenportalservice.repository;

import com.sambit.citizenportalservice.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface URLRepository extends JpaRepository<URL, Long> {

    @Query(value = "FROM URL u WHERE u.user.userId = ?1 AND u.status = true ORDER BY u.urlGroup.urlGroupId")
    List<URL> findAllByUserId(Long userId);

    @Query(value = "SELECT DISTINCT u.urlGroup.urlGroupName FROM URL u WHERE u.user.userId = ?1 AND u.status = true ORDER BY u.urlGroup.urlGroupId")
    List<String> findDistinctURLGroupNameByUserId(Long userId);

}