package com.nikku.hospital_management_system.service;

import com.nikku.hospital_management_system.dto.InsuranceRequestDto;
import com.nikku.hospital_management_system.dto.InsuranceResponseDto;
import com.nikku.hospital_management_system.entity.Insurance;
import com.nikku.hospital_management_system.entity.Patient;
import com.nikku.hospital_management_system.entity.User;
import com.nikku.hospital_management_system.repository.InsuranceRepository;
import com.nikku.hospital_management_system.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;


    public InsuranceResponseDto assignInsuranceToPatient(
            InsuranceRequestDto request,
            User user
    ) {

        Patient patient = patientRepository.findByUser(user);


        if (patient.getInsurance() != null) {
            throw new IllegalStateException("Patient already has insurance");
        }

        Insurance insurance = Insurance.builder()
                .policyNumber(request.getPolicyNumber())
                .provider(request.getProvider())
                .validUntil(request.getValidUntil())
                .build();

        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        return mapToResponse(insurance, patient);
    }

    private InsuranceResponseDto mapToResponse(
            Insurance insurance,
            Patient patient
    ) {
        return new InsuranceResponseDto(
                insurance.getId(),
                insurance.getPolicyNumber(),
                insurance.getProvider(),
                insurance.getValidUntil(),
                patient.getId()
        );
    }

    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(null);
        return patient;
    }

    public InsuranceResponseDto getInsuranceByUser(User user) {
        Patient patient = patientRepository.findById(user.getId()).orElseThrow();
        Insurance insurance  = insuranceRepository.findByPatient(patient);
         return mapToResponse(insurance, patient);
    }

}
