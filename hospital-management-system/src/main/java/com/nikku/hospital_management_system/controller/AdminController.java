package com.nikku.hospital_management_system.controller;

import com.nikku.hospital_management_system.dto.requestDto.*;
import com.nikku.hospital_management_system.dto.responseDto.*;
import com.nikku.hospital_management_system.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // ================= DOCTORS =================

    @PostMapping("/doctors")
    public ResponseEntity<DoctorResponseDto>
    createDoctor(@RequestBody CreateDoctorRequestDto dto) {

        return ResponseEntity.ok(
                adminService.createDoctor(dto)
        );
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponseDto>>
    getAllDoctors() {

        return ResponseEntity.ok(
                adminService.getAllDoctors()
        );
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorResponseDto>
    getDoctorById(@PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.getDoctorById(id)
        );
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<String>
    deleteDoctor(@PathVariable Long id) {

        adminService.deleteDoctor(id);

        return ResponseEntity.ok("Doctor deleted");
    }

    // ================= DEPARTMENTS =================

    @PostMapping("/departments")
    public ResponseEntity<DepartmentResponseDto>
    createDepartment(@RequestBody DepartmentRequestDto dto) {

        return ResponseEntity.ok(
                adminService.createDepartment(dto)
        );
    }

    @PutMapping("/departments/{id}/head/{doctorId}")
    public ResponseEntity<String>
    assignHeadDoctor(
            @PathVariable Long id,
            @PathVariable Long doctorId
    ) {

        adminService.assignHeadDoctor(id, doctorId);

        return ResponseEntity.ok("Head doctor assigned");
    }

    @PutMapping("/departments/{id}/doctors/{doctorId}")
    public ResponseEntity<String>
    addDoctorToDepartment(
            @PathVariable Long id,
            @PathVariable Long doctorId
    ) {

        adminService.addDoctorToDepartment(id, doctorId);

        return ResponseEntity.ok("Doctor added to department");
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentResponseDto>>
    getAllDepartments() {

        return ResponseEntity.ok(
                adminService.getAllDepartments()
        );
    }

    // ================= PATIENTS =================

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>>
    getAllPatients() {

        return ResponseEntity.ok(
                adminService.getAllPatients()
        );
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientResponseDto>
    getPatientById(@PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.getPatientById(id)
        );
    }

    // ================= APPOINTMENTS =================

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>>
    getAllAppointments() {

        return ResponseEntity.ok(
                adminService.getAllAppointments()
        );
    }
}
