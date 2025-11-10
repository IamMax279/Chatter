package com.max420.identity_service.infrastructure.adapters.out.persistence;

import com.max420.identity_service.domain.models.user.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique = true)
    private String email;
    @NonNull
    private String password;
    @NonNull
    @Column(unique = true)
    private String username;
    @NonNull
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @Column(columnDefinition = "boolean default true")
    private boolean isActive = true;
    @Column(columnDefinition = "boolean default false")
    private boolean isVerified = false;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
