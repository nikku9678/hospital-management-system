package com.nikku.hospital_management_system.controller;

import com.nikku.hospital_management_system.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

//    @PostMapping("/book")
//    public AppointmentResponse book(
//            @RequestBody BookAppointmentRequest request) {
//
//        return appointmentService.book(request);
//    }
//
//    @GetMapping("/my")
//    public List<AppointmentResponse> myAppointments() {
//        return appointmentService.myAppointments();
//    }
}
