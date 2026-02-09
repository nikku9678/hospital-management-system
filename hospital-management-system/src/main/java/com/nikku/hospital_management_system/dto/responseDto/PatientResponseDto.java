package com.nikku.hospital_management_system.dto.responseDto;

import com.nikku.hospital_management_system.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String adharNumber;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}
