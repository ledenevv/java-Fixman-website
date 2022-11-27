package com.example.application.data.repository;

import com.example.application.data.entity.ServicesList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ServicesListRepository extends JpaRepository<ServicesList, Long> {
   @Query(value = "SELECT s FROM service_list")
    List<ServicesList> getAllDataFromServices();
}
