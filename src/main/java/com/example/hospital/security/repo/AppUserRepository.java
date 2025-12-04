package com.example.hospital.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hospital.security.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
