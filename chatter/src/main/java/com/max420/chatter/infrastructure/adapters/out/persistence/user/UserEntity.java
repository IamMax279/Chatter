package com.max420.chatter.infrastructure.adapters.out.persistence.user;

import com.max420.chatter.domain.models.user.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
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
    @Builder.Default
    private String bio = "";
    @NonNull
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
    private boolean isActive;
    private boolean isVerified;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
