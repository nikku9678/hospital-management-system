package com.nikku.hospital_management_system.dto;

import com.nikku.hospital_management_system.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserProfileResponse {

    private Long id;
    private String username;
    private String email;
    private String role;

    public static UserProfileResponse from(User user) {
        UserProfileResponse dto = new UserProfileResponse();
        dto.id = user.getId();
        dto.username = user.getUsername();
        dto.email = user.getEmail();
        dto.role = user.getRoles().name();
        return dto;
    }
}
