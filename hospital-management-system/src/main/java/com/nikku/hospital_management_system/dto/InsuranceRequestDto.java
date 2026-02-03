package com.nikku.hospital_management_system.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InsuranceRequestDto {
    private String policyNumber;
    private String provider;
    private LocalDate validUntil;

}
