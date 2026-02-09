package com.nikku.hospital_management_system.dto.requestDto;

import lombok.Data;

@Data
public class CreateDoctorRequestDto {

    private Long userId;
    private String name;
    private String specialization;
    private String email;
}
