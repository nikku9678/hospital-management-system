package com.nikku.hospital_management_system.controller;


import com.nikku.hospital_management_system.dto.requestDto.InsuranceRequestDto;
import com.nikku.hospital_management_system.dto.responseDto.InsuranceResponseDto;
import com.nikku.hospital_management_system.entity.User;
import com.nikku.hospital_management_system.security.LoggedInUser;
import com.nikku.hospital_management_system.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @GetMapping("")
    public ResponseEntity<InsuranceResponseDto> getInsurance(){

        User user = LoggedInUser.getCurrentLoggedInUser();
        InsuranceResponseDto insuranceResponseDto = insuranceService.getInsuranceByUser(user);
        System.out.println("USER -> " + user);
        return ResponseEntity.status(HttpStatus.OK).body(insuranceResponseDto);
    }

    @PostMapping("/add")
    public ResponseEntity<InsuranceResponseDto> assignInsuranceToPatient(
            @RequestBody InsuranceRequestDto request
    ) {
        User user = LoggedInUser.getCurrentLoggedInUser();

        InsuranceResponseDto response =
                insuranceService.assignInsuranceToPatient(request, user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
