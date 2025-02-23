package com.busbooking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busbooking.api.model.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsByName(String name);
}
