package com.moa.synthizer_backend.notebook.dto;

import java.util.UUID;

public record NotebookResponse(
        UUID id,
        String title,
        String description
) {
}
