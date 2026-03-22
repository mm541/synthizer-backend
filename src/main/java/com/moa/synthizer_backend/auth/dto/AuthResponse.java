package com.moa.synthizer_backend.auth.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        String tokenType
) {
}
