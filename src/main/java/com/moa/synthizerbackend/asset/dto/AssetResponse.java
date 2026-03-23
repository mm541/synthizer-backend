package com.moa.synthizerbackend.asset.dto;

import java.util.UUID;

public record AssetResponse(
        UUID id,
        String name,
        String type
) {
}
