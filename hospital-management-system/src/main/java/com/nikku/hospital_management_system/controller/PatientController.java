package com.nikku.hospital_management_system.controller;

import com.nikku.hospital_management_system.dto.responseDto.AppointmentResponseDto;
import com.nikku.hospital_management_system.dto.requestDto.CreateAppointmentRequestDto;
import com.nikku.hospital_management_system.dto.responseDto.PatientResponseDto;
import com.nikku.hospital_management_system.service.AppointmentService;
import com.nikku.hospital_management_system.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    //get All patients
    @GetMapping("")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }



    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile")
    private ResponseEntity<PatientResponseDto> getPatientProfile() {
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

}
