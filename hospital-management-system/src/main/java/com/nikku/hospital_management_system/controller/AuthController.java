package com.nikku.hospital_management_system.controller;

import com.nikku.hospital_management_system.dto.requestDto.LoginRequestDto;
import com.nikku.hospital_management_system.dto.requestDto.SignUpRequestDto;
import com.nikku.hospital_management_system.dto.responseDto.LoginResponseDto;
import com.nikku.hospital_management_system.dto.responseDto.SignupResponseDto;
import com.nikku.hospital_management_system.dto.responseDto.UserProfileResponseDto;
import com.nikku.hospital_management_system.entity.User;
import com.nikku.hospital_management_system.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignUpRequestDto signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }

    @GetMapping("/me")
    public UserProfileResponseDto getMyProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth.getPrincipal() instanceof User user)) {
            throw new IllegalStateException("User not authenticated");
        }

        System.out.println("USER PROFILE -> " + user);
        return UserProfileResponseDto.from(user);
    }

}
