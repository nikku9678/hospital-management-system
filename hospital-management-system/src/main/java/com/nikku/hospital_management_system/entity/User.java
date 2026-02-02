package com.nikku.hospital_management_system.entity;

import com.nikku.hospital_management_system.entity.type.AuthProviderType;
import com.nikku.hospital_management_system.entity.type.RoleType;
import com.nikku.hospital_management_system.security.RolePermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "app_user", indexes = {
        @Index(name = "idx_provider_id_provider_type", columnList = "providerId, providerType")
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private AuthProviderType providerType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // ROLE_
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.name()));

        // permissions for this role
        authorities.addAll(
                RolePermissionMapping.getAuthoritiesForRole(roles)
        );

        return authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", providerType=" + providerType +
                ", roles=" + roles +
                '}';
    }

}



















