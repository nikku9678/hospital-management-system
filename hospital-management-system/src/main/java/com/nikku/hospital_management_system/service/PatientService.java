package com.nikku.hospital_management_system.service;

import com.nikku.hospital_management_system.dto.PatientResponseDto;
import com.nikku.hospital_management_system.entity.Patient;
import com.nikku.hospital_management_system.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PatientResponseDto getPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient Not " +
                "Found with id: " + patientId));
        return modelMapper.map(patient, PatientResponseDto.class);
    }

    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {
        return null;
    }

    public List<PatientResponseDto> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(patient -> {
                    return new PatientResponseDto(
                            patient.getId(),
                            patient.getName(),
                            patient.getGender(),
                            patient.getPhoneNumber(),
                            patient.getAdharNumber(),
                            patient.getBirthDate(),
                            patient.getBloodGroup()
                            );
                })
                .toList();
    }

}
