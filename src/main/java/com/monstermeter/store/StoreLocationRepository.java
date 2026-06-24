package com.monstermeter.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLocationRepository extends JpaRepository<StoreLocation, Long> {
    List<StoreLocation> findByAddedById(Long userId);
}
