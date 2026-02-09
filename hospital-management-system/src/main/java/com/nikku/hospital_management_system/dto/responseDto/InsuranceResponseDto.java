package com.nikku.hospital_management_system.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceResponseDto {

    private Long id;
    private String policyNumber;
    private String provider;
    private LocalDate validUntil;
    private Long patientId;
}
