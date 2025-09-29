package com.max420.Scrapr_server.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
