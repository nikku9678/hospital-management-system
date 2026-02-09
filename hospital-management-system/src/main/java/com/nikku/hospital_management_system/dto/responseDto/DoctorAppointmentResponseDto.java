package com.nikku.hospital_management_system.dto.responseDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoctorAppointmentResponseDto {

    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;

    private Long patientId;
    private String patientName;
}
