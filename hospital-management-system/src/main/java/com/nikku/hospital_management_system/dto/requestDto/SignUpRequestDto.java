package com.nikku.hospital_management_system.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String password;
    private String name;
    private String email;

//    private Set<RoleType> roles = new HashSet<>();
}
