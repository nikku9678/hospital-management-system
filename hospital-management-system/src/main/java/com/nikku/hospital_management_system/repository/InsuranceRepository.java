package com.nikku.hospital_management_system.repository;

import com.nikku.hospital_management_system.entity.Insurance;
import com.nikku.hospital_management_system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    public Insurance findByPatient(Patient patient);
}