package com.nikku.hospital_management_system.repository;

import com.nikku.hospital_management_system.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}