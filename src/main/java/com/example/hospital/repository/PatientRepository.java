package com.example.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hospital.entities.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface PatientRepository extends JpaRepository<Patient, Long> {
      Page<Patient> findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(String nom, String prenom, Pageable pageable);
}
