package com.moa.synthizerbackend.generation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GenerationRequest(
        @NotBlank
        @Size(max = 2000)
        String prompt
) {
}
