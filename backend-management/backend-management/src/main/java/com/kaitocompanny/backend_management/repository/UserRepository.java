package com.kaitocompanny.backend_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kaitocompanny.backend_management.model.Users;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(String email);
}