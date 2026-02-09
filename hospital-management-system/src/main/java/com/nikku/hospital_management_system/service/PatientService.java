package com.nikku.hospital_management_system.service;

import com.nikku.hospital_management_system.dto.requestDto.*;
import com.nikku.hospital_management_system.dto.responseDto.*;

import java.util.List;

public interface PatientService {

    // Profile
    PatientResponseDto getPatientByUserId(Long userId);
    PatientResponseDto updatePatientProfile(Long userId, UpdatePatientRequestDto dto);

    // Insurance
    InsuranceResponseDto addInsurance(Long userId, CreateInsuranceRequestDto dto);
    InsuranceResponseDto getInsurance(Long userId);
    InsuranceResponseDto updateInsurance(Long userId, CreateInsuranceRequestDto dto);
    void deleteInsurance(Long userId);

    // Appointment
    AppointmentResponseDto bookAppointment(Long userId, CreateAppointmentRequestDto dto);
    List<AppointmentResponseDto> getMyAppointments(Long userId);
    AppointmentResponseDto getAppointmentById(Long appointmentId);
    void cancelAppointment(Long appointmentId);
}
