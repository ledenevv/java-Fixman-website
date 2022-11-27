package com.example.application.data.repository;

import com.example.application.data.entity.ServicesList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ServicesListRepository extends JpaRepository<ServicesList, Long> {

    @Query("select s from ServicesList s " +
            "where lower(s.serviceName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(s.serviceDescr) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(s.price) like lower(concat('%', :searchTerm, '%'))")
    List<ServicesList> search(@Param("searchTerm") String searchTerm);
}
