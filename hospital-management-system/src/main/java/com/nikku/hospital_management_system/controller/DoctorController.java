package com.nikku.hospital_management_system.controller;

import com.nikku.hospital_management_system.dto.requestDto.UpdateDoctorRequestDto;
import com.nikku.hospital_management_system.dto.responseDto.*;
import com.nikku.hospital_management_system.entity.User;
import com.nikku.hospital_management_system.security.LoggedInUser;
import com.nikku.hospital_management_system.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // ================= PROFILE =================

    @GetMapping("/profile")
    public ResponseEntity<DoctorResponseDto> getMyProfile() {

        User user = LoggedInUser.getCurrentLoggedInUser();

        return ResponseEntity.ok(
                doctorService.getDoctorProfile(user.getId())
        );
    }

    @PutMapping("/profile")
    public ResponseEntity<DoctorResponseDto> updateProfile(
            @RequestBody UpdateDoctorRequestDto dto
    ) {

        User user = LoggedInUser.getCurrentLoggedInUser();

        return ResponseEntity.ok(
                doctorService.updateDoctorProfile(user.getId(), dto)
        );
    }

    // ================= APPOINTMENTS =================

    @GetMapping("/appointments")
    public ResponseEntity<List<DoctorAppointmentResponseDto>>
    getMyAppointments() {

        User user = LoggedInUser.getCurrentLoggedInUser();

        return ResponseEntity.ok(
                doctorService.getDoctorAppointments(user.getId())
        );
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<DoctorAppointmentResponseDto>
    getAppointmentById(@PathVariable Long id) {

        return ResponseEntity.ok(
                doctorService.getAppointmentById(id)
        );
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity<DoctorAppointmentResponseDto>
    updateAppointment(
            @PathVariable Long id,
            @RequestBody String reason
    ) {

        return ResponseEntity.ok(
                doctorService.updateAppointmentReason(id, reason)
        );
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<String> cancelAppointment(
            @PathVariable Long id
    ) {

        doctorService.cancelAppointment(id);

        return ResponseEntity.ok("Appointment cancelled");
    }
}
