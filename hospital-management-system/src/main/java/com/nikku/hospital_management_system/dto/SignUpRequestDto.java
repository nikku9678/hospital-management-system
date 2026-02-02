package com.nikku.hospital_management_system.dto;

import com.nikku.hospital_management_system.entity.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
