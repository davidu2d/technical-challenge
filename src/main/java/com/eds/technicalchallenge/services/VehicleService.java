package com.eds.technicalchallenge.services;

import com.eds.technicalchallenge.domain.vehicle.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleService {
    Vehicle create(Vehicle vehicle);
    Page<Vehicle> findAll(String name, String brand, Integer yearInitial, Integer yearFinal, BigDecimal priceInitial, BigDecimal priceFinal, Pageable pageable);
    Vehicle findById(Long id);
    void update(Long id, Vehicle vehicle);
    void delete(Long id);
    void sell(Long id);
}
