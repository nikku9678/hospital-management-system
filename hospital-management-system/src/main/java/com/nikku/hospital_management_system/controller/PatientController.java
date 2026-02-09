package com.nikku.hospital_management_system.controller;

import com.nikku.hospital_management_system.dto.requestDto.CreateAppointmentRequestDto;
import com.nikku.hospital_management_system.dto.requestDto.CreateInsuranceRequestDto;
import com.nikku.hospital_management_system.dto.requestDto.UpdatePatientRequestDto;
import com.nikku.hospital_management_system.dto.responseDto.*;
import com.nikku.hospital_management_system.entity.User;
import com.nikku.hospital_management_system.security.LoggedInUser;
import com.nikku.hospital_management_system.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // ================= PROFILE =================

    @GetMapping("/profile")
    public ResponseEntity<PatientResponseDto> getProfile() {

        User user = LoggedInUser.getCurrentLoggedInUser();

        return ResponseEntity.ok(
                patientService.getPatientByUserId(user.getId())
        );
    }

    @PutMapping("/profile")
    public ResponseEntity<PatientResponseDto> updateProfile(
            @RequestBody UpdatePatientRequestDto dto
    ) {

        User user = LoggedInUser.getCurrentLoggedInUser();

        return ResponseEntity.ok(
                patientService.updatePatientProfile(user.getId(), dto)
        );
    }

    // ================= INSURANCE =================

    @PostMapping("/insurance")
    public ResponseEntity<InsuranceResponseDto> addInsurance(
            @RequestBody CreateInsuranceRequestDto dto
    ) {

        User user = LoggedInUser.getCurrentLoggedInUser();

        return ResponseEntity.ok(
                patientService.addInsurance(user.getId(), dto)
        );
    }

    @GetMapping("/insurance")
    public ResponseEntity<InsuranceResponseDto> getInsurance() {

        User user = LoggedInUser.getCurrentLoggedInUser();

        return ResponseEntity.ok(
                patientService.getInsurance(user.getId())
        );
    }

    @PutMapping("/insurance")
    public ResponseEntity<InsuranceResponseDto> updateInsurance(
            @RequestBody CreateInsuranceRequestDto dto
    ) {

        User user = LoggedInUser.getCurrentLoggedInUser();

        return ResponseEntity.ok(
                patientService.updateInsurance(user.getId(), dto)
        );
    }

    @DeleteMapping("/insurance")
    public ResponseEntity<String> deleteInsurance() {

        User user = LoggedInUser.getCurrentLoggedInUser();

        patientService.deleteInsurance(user.getId());

        return ResponseEntity.ok("Insurance deleted successfully");
    }

    // ================= APPOINTMENTS =================

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> bookAppointment(
            @RequestBody CreateAppointmentRequestDto dto
    ) {

        User user = LoggedInUser.getCurrentLoggedInUser();

        return ResponseEntity.ok(
                patientService.bookAppointment(user.getId(), dto)
        );
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getMyAppointments() {

        User user = LoggedInUser.getCurrentLoggedInUser();

        return ResponseEntity.ok(
                patientService.getMyAppointments(user.getId())
        );
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                patientService.getAppointmentById(id)
        );
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<String> cancelAppointment(
            @PathVariable Long id
    ) {

        patientService.cancelAppointment(id);

        return ResponseEntity.ok("Appointment cancelled successfully");
    }
}
