package com.nikku.hospital_management_system.dto.requestDto;

import lombok.Data;

import java.time.LocalDate;


@Data

public class CreateInsuranceRequestDto {

    private String policyNumber;
    private String provider;
    private LocalDate validUntil;
}