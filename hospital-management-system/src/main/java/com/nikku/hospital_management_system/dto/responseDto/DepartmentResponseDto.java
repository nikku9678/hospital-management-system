package com.nikku.hospital_management_system.dto.responseDto;

import lombok.Data;

@Data
public class DepartmentResponseDto {

    private Long id;
    private String name;
    private String headDoctorName;
}
