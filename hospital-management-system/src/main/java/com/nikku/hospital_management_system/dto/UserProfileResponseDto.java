package com.nikku.hospital_management_system.dto;

import com.nikku.hospital_management_system.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserProfileResponseDto {

    private Long id;
    private String username;
    private String email;
    private String role;

    public static UserProfileResponseDto from(User user) {
        if (user == null) {
            return null; // Handle potential null input
        }

        UserProfileResponseDto dto = new UserProfileResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRoles().name()); // Assuming User has a getRole() method

        return dto;
    }
}
