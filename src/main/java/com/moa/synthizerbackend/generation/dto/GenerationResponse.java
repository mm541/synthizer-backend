package com.moa.synthizerbackend.generation.dto;

import java.util.UUID;

public record GenerationResponse(
        UUID id,
        String status,
        String prompt
) {
}
