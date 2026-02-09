package com.nikku.hospital_management_system.service;

import com.nikku.hospital_management_system.dto.requestDto.UpdateDoctorRequestDto;
import com.nikku.hospital_management_system.dto.responseDto.*;

import java.util.List;

public interface DoctorService {

    // Profile
    DoctorResponseDto getDoctorProfile(Long userId);

    DoctorResponseDto updateDoctorProfile(
            Long userId,
            UpdateDoctorRequestDto dto
    );

    // Appointments
    List<DoctorAppointmentResponseDto>
    getDoctorAppointments(Long userId);

    DoctorAppointmentResponseDto
    getAppointmentById(Long appointmentId);

    DoctorAppointmentResponseDto
    updateAppointmentReason(Long appointmentId, String reason);

    void cancelAppointment(Long appointmentId);
}
