package com.nikku.hospital_management_system.service;

import com.nikku.hospital_management_system.dto.requestDto.*;
import com.nikku.hospital_management_system.dto.responseDto.*;

import java.util.List;

public interface AdminService {

    // Doctors
    DoctorResponseDto createDoctor(CreateDoctorRequestDto dto);
    List<DoctorResponseDto> getAllDoctors();
    DoctorResponseDto getDoctorById(Long id);
    void deleteDoctor(Long id);

    // Departments
    DepartmentResponseDto createDepartment(DepartmentRequestDto dto);
    void assignHeadDoctor(Long deptId, Long doctorId);
    void addDoctorToDepartment(Long deptId, Long doctorId);
    List<DepartmentResponseDto> getAllDepartments();

    // Patients
    List<PatientResponseDto> getAllPatients();
    PatientResponseDto getPatientById(Long id);

    // Appointments
    List<AppointmentResponseDto> getAllAppointments();
}
