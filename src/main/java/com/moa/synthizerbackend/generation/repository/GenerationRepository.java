package com.moa.synthizerbackend.generation.repository;

import com.moa.synthizerbackend.generation.entity.Generation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenerationRepository extends JpaRepository<Generation, UUID> {
}
