package com.moa.synthizer_backend.notebook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NotebookRequest(
        @NotBlank
        @Size(max = 255)
        String title,

        @Size(max = 1000)
        String description
) {
}
