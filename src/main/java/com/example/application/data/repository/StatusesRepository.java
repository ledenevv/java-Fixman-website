package com.example.application.data.repository;

import com.example.application.data.entity.Statuses;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusesRepository extends JpaRepository<Statuses, Long> {

}
