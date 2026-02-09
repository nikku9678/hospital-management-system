package com.nikku.hospital_management_system.service.impl;

import com.nikku.hospital_management_system.dto.requestDto.*;
import com.nikku.hospital_management_system.dto.responseDto.*;
import com.nikku.hospital_management_system.entity.*;
import com.nikku.hospital_management_system.repository.*;
import com.nikku.hospital_management_system.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    // =========================================================
    // 🔹 PROFILE
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public PatientResponseDto getPatientByUserId(Long userId) {

        Patient patient = getPatientEntityByUserId(userId);

        return mapPatientToDto(patient);
    }


    @Override
    public PatientResponseDto updatePatientProfile(
            Long userId,
            UpdatePatientRequestDto dto
    ) {

        Patient patient = getPatientEntityByUserId(userId);

        patient.setName(dto.getName());
        patient.setBirthDate(dto.getBirthDate());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setGender(dto.getGender());
        patient.setAdharNumber(dto.getAdharNumber());
        patient.setBloodGroup(dto.getBloodGroup());

        return mapPatientToDto(patient);
    }

    // =========================================================
    // 🔹 INSURANCE
    // =========================================================

    @Override
    public InsuranceResponseDto addInsurance(
            Long userId,
            CreateInsuranceRequestDto dto
    ) {

        Patient patient = getPatientEntityByUserId(userId);

        Insurance insurance = Insurance.builder()
                .policyNumber(dto.getPolicyNumber())
                .provider(dto.getProvider())
                .validUntil(dto.getValidUntil())
                .patient(patient)
                .build();

        patient.setInsurance(insurance);

        insuranceRepository.save(insurance);

        return mapInsuranceToDto(insurance);
    }


    @Override
    @Transactional(readOnly = true)
    public InsuranceResponseDto getInsurance(Long userId) {

        Patient patient = getPatientEntityByUserId(userId);

        Insurance insurance = patient.getInsurance();

        if (insurance == null) {
            throw new EntityNotFoundException(
                    "Insurance not found for patient"
            );
        }

        return mapInsuranceToDto(insurance);
    }


    @Override
    public InsuranceResponseDto updateInsurance(
            Long userId,
            CreateInsuranceRequestDto dto
    ) {

        Patient patient = getPatientEntityByUserId(userId);

        Insurance insurance = patient.getInsurance();

        if (insurance == null) {
            throw new EntityNotFoundException(
                    "Insurance not found to update"
            );
        }

        insurance.setPolicyNumber(dto.getPolicyNumber());
        insurance.setProvider(dto.getProvider());
        insurance.setValidUntil(dto.getValidUntil());

        return mapInsuranceToDto(insurance);
    }


    @Override
    public void deleteInsurance(Long userId) {

        Patient patient = getPatientEntityByUserId(userId);

        Insurance insurance = patient.getInsurance();

        if (insurance == null) {
            throw new EntityNotFoundException(
                    "Insurance not found to delete"
            );
        }

        patient.setInsurance(null);

        insuranceRepository.delete(insurance);
    }

    // =========================================================
    // 🔹 APPOINTMENTS
    // =========================================================

    @Override
    public AppointmentResponseDto bookAppointment(
            Long userId,
            CreateAppointmentRequestDto dto
    ) {

        Patient patient = getPatientEntityByUserId(userId);

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Doctor not found with id: "
                                        + dto.getDoctorId()
                        ));

        Appointment appointment = Appointment.builder()
                .appointmentTime(dto.getAppointmentTime())
                .reason(dto.getReason())
                .patient(patient)
                .doctor(doctor)
                .build();

        appointmentRepository.save(appointment);

        return mapAppointmentToDto(appointment);
    }


    @Override
    @Transactional(readOnly = true)
    public List<AppointmentResponseDto> getMyAppointments(
            Long userId
    ) {

        Patient patient = getPatientEntityByUserId(userId);

        return appointmentRepository
                .findByPatientId(patient.getId())
                .stream()
                .map(this::mapAppointmentToDto)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public AppointmentResponseDto getAppointmentById(
            Long appointmentId
    ) {

        Appointment appointment =
                appointmentRepository.findById(appointmentId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Appointment not found"
                                ));

        return mapAppointmentToDto(appointment);
    }


    @Override
    public void cancelAppointment(Long appointmentId) {

        Appointment appointment =
                appointmentRepository.findById(appointmentId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Appointment not found"
                                ));

        appointmentRepository.delete(appointment);
    }

    // =========================================================
    // 🔹 PRIVATE HELPERS
    // =========================================================

    private Patient getPatientEntityByUserId(Long userId) {

        return patientRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Patient not found for user id: "
                                        + userId
                        ));
    }


    private PatientResponseDto mapPatientToDto(
            Patient patient
    ) {
        return modelMapper.map(
                patient,
                PatientResponseDto.class
        );
    }


    private InsuranceResponseDto mapInsuranceToDto(
            Insurance insurance
    ) {
        return modelMapper.map(
                insurance,
                InsuranceResponseDto.class
        );
    }


    private AppointmentResponseDto mapAppointmentToDto(
            Appointment appointment
    ) {

        AppointmentResponseDto dto =
                modelMapper.map(
                        appointment,
                        AppointmentResponseDto.class
                );

        dto.setDoctorId(
                appointment.getDoctor().getId()
        );

        dto.setDoctorName(
                appointment.getDoctor().getName()
        );

        return dto;
    }
}
