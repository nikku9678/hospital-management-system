package com.nikku.hospital_management_system.dto.requestDto;

import lombok.Data;

@Data
public class UpdateDoctorRequestDto {

    private String name;
    private String specialization;
    private String email;
}
