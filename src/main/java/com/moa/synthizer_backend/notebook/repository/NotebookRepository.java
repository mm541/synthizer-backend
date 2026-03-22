package com.moa.synthizer_backend.notebook.repository;

import com.moa.synthizer_backend.notebook.entity.Notebook;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, UUID> {
}
