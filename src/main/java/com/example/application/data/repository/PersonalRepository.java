package com.example.application.data.repository;

import com.example.application.data.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
