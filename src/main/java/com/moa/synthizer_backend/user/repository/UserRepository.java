package com.moa.synthizer_backend.user.repository;

import com.moa.synthizer_backend.user.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
}
