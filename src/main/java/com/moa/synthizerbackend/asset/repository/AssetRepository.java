package com.moa.synthizerbackend.asset.repository;

import com.moa.synthizerbackend.asset.entity.Asset;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, UUID> {
}
