package com.eds.technicalchallenge.services;

import com.eds.technicalchallenge.domain.vehicle.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleService {
    Vehicle create(Vehicle vehicle);
    Page<Vehicle> findAll(Pageable pageable);
    Vehicle findById(Long id);
    void update(Long id, Vehicle vehicle);
    void delete(Long id);
    void sell(Long id);
}
