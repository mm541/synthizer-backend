package com.moa.synthizerbackend.notebook.dto;

import java.util.UUID;

public record NotebookResponse(
        UUID id,
        String title,
        String description
) {
}
