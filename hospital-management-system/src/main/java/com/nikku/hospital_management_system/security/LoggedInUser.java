package com.nikku.hospital_management_system.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.nikku.hospital_management_system.entity.User;

public class LoggedInUser {

    public static User getCurrentLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !(auth.getPrincipal() instanceof User user)) {
            throw new IllegalStateException("User not authenticated");
        }
        return user;
    }
}
