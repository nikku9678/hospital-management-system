package com.nikku.hospital_management_system.dto.requestDto;

import com.nikku.hospital_management_system.entity.type.BloodGroupType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatePatientRequestDto {

    private String name;
    private LocalDate birthDate;
    private String phoneNumber;
    private String gender;
    private String adharNumber;
    private BloodGroupType bloodGroup;
}
