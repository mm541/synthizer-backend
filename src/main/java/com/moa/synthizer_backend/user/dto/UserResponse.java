package com.moa.synthizer_backend.user.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        String fullName
) {
}
