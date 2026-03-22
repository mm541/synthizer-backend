package com.moa.synthizer_backend.common.response;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}
