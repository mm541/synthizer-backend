package com.moa.synthizerbackend.notebook.repository;

import com.moa.synthizerbackend.notebook.entity.Notebook;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, UUID> {
}
