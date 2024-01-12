package com.ajay.InstituteManagementSystemApp.repositories;

import com.ajay.InstituteManagementSystemApp.entities.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstituteRepository extends JpaRepository<Institute,Long> {
    Optional<Institute> findByName(String name);

    Institute findByEmailId(String emailId);
}
