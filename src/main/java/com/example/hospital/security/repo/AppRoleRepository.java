package com.example.hospital.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hospital.security.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {
    
}
