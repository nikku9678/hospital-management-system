package com.nikku.hospital_management_system.dto;

import lombok.Data;

@Data
public class OnboardDoctorRequestDto {
    private Long userId;
    private String specialization;
    private String name;
}
