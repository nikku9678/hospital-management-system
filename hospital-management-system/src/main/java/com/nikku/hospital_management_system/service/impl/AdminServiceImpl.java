package com.nikku.hospital_management_system.service.impl;

import com.nikku.hospital_management_system.dto.requestDto.*;
import com.nikku.hospital_management_system.dto.responseDto.*;
import com.nikku.hospital_management_system.entity.*;
import com.nikku.hospital_management_system.repository.*;
import com.nikku.hospital_management_system.service.AdminService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // =====================================================
    // DOCTORS
    // =====================================================

    @Override
    public DoctorResponseDto createDoctor(
            CreateDoctorRequestDto dto
    ) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found")
                );

        Doctor doctor = Doctor.builder()
                .user(user)
                .name(dto.getName())
                .specialization(dto.getSpecialization())
                .email(dto.getEmail())
                .build();

        doctorRepository.save(doctor);

        return modelMapper.map(doctor, DoctorResponseDto.class);
    }


    @Override
    public List<DoctorResponseDto> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(d -> modelMapper.map(d, DoctorResponseDto.class))
                .toList();
    }


    @Override
    public DoctorResponseDto getDoctorById(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Doctor not found")
                );

        return modelMapper.map(doctor, DoctorResponseDto.class);
    }


    @Override
    public void deleteDoctor(Long id) {

        doctorRepository.deleteById(id);
    }

    // =====================================================
    // DEPARTMENTS
    // =====================================================

    @Override
    public DepartmentResponseDto createDepartment(
            DepartmentRequestDto dto
    ) {

        Department department = new Department();
        department.setName(dto.getName());

        departmentRepository.save(department);

        return mapDepartment(department);
    }


    @Override
    public void assignHeadDoctor(Long deptId, Long doctorId) {

        Department dept = getDepartment(deptId);
        Doctor doctor = getDoctor(doctorId);

        dept.setHeadDoctor(doctor);
    }


    @Override
    public void addDoctorToDepartment(
            Long deptId,
            Long doctorId
    ) {

        Department dept = getDepartment(deptId);
        Doctor doctor = getDoctor(doctorId);

        dept.getDoctors().add(doctor);
    }


    @Override
    public List<DepartmentResponseDto> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(this::mapDepartment)
                .toList();
    }

    // =====================================================
    // PATIENTS
    // =====================================================

    @Override
    public List<PatientResponseDto> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, PatientResponseDto.class))
                .toList();
    }


    @Override
    public PatientResponseDto getPatientById(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Patient not found")
                );

        return modelMapper.map(patient, PatientResponseDto.class);
    }

    // =====================================================
    // APPOINTMENTS
    // =====================================================

    @Override
    public List<AppointmentResponseDto> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(a -> modelMapper.map(a, AppointmentResponseDto.class))
                .toList();
    }

    // =====================================================
    // HELPERS
    // =====================================================

    private Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Department not found")
                );
    }

    private Doctor getDoctor(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Doctor not found")
                );
    }

    private DepartmentResponseDto mapDepartment(
            Department dept
    ) {

        DepartmentResponseDto dto =
                modelMapper.map(dept, DepartmentResponseDto.class);

        if (dept.getHeadDoctor() != null) {
            dto.setHeadDoctorName(
                    dept.getHeadDoctor().getName()
            );
        }

        return dto;
    }
}
