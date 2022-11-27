package com.example.application.data.repository;

import com.example.application.data.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
