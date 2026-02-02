package com.nikku.hospital_management_system.repository;

import com.nikku.hospital_management_system.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}