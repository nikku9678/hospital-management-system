package com.nikku.hospital_management_system.service.impl;

import com.nikku.hospital_management_system.dto.requestDto.UpdateDoctorRequestDto;
import com.nikku.hospital_management_system.dto.responseDto.*;
import com.nikku.hospital_management_system.entity.*;
import com.nikku.hospital_management_system.repository.*;
import com.nikku.hospital_management_system.service.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    // =====================================================
    // PROFILE
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public DoctorResponseDto getDoctorProfile(Long userId) {

        Doctor doctor = getDoctorByUserId(userId);

        return modelMapper.map(doctor, DoctorResponseDto.class);
    }


    @Override
    public DoctorResponseDto updateDoctorProfile(
            Long userId,
            UpdateDoctorRequestDto dto
    ) {

        Doctor doctor = getDoctorByUserId(userId);

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setEmail(dto.getEmail());

        return modelMapper.map(doctor, DoctorResponseDto.class);
    }

    // =====================================================
    // APPOINTMENTS
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public List<DoctorAppointmentResponseDto>
    getDoctorAppointments(Long userId) {

        Doctor doctor = getDoctorByUserId(userId);

        return appointmentRepository
                .findByDoctorId(doctor.getId())
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public DoctorAppointmentResponseDto
    getAppointmentById(Long appointmentId) {

        Appointment appointment =
                appointmentRepository.findById(appointmentId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Appointment not found"
                                ));

        return mapToDto(appointment);
    }


    @Override
    public DoctorAppointmentResponseDto
    updateAppointmentReason(
            Long appointmentId,
            String reason
    ) {

        Appointment appointment =
                appointmentRepository.findById(appointmentId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Appointment not found"
                                ));

        appointment.setReason(reason);

        return mapToDto(appointment);
    }


    @Override
    public void cancelAppointment(Long appointmentId) {

        Appointment appointment =
                appointmentRepository.findById(appointmentId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Appointment not found"
                                ));

        appointmentRepository.delete(appointment);
    }

    // =====================================================
    // HELPERS
    // =====================================================

    private Doctor getDoctorByUserId(Long userId) {

        return doctorRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Doctor not found for user id: "
                                        + userId
                        ));
    }


    private DoctorAppointmentResponseDto
    mapToDto(Appointment appointment) {

        DoctorAppointmentResponseDto dto =
                modelMapper.map(
                        appointment,
                        DoctorAppointmentResponseDto.class
                );

        dto.setPatientId(
                appointment.getPatient().getId()
        );

        dto.setPatientName(
                appointment.getPatient().getName()
        );

        return dto;
    }
}
