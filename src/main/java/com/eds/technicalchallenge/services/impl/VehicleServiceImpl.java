package com.eds.technicalchallenge.services.impl;

import com.eds.technicalchallenge.domain.vehicle.Vehicle;
import com.eds.technicalchallenge.exceptions.ResourceNotFoundException;
import com.eds.technicalchallenge.exceptions.VehicleWithInvalidYearException;
import com.eds.technicalchallenge.exceptions.VehicleWithSameChassisException;
import com.eds.technicalchallenge.repositories.VehicleRepository;
import com.eds.technicalchallenge.services.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle create(Vehicle vehicle) {
        validateVehicleWithSameChassis(vehicle.getChassis());
        validateVehicleWithYearGreaterThanCurrentYear(vehicle.getYear());
        return this.vehicleRepository.save(vehicle);
    }

    @Override
    public Page<Vehicle> findAll(String name, String brand, Integer yearInitial, Integer yearFinal, BigDecimal priceInitial, BigDecimal priceFinal, Pageable pageable) {
        return this.vehicleRepository.findByNameAndBrandAndYearAndPrice(name, brand, yearInitial, yearFinal, priceInitial, priceFinal, pageable);
    }

    @Override
    public Vehicle findById(Long id) {
        var vehicle = this.vehicleRepository.findById(id);
        if(vehicle.isEmpty()) throw new ResourceNotFoundException(Vehicle.class);
        return vehicle.get();
    }

    @Override
    public void update(Long id, Vehicle vehicle) {
        var recoveredVehicle = this.findById(id);
        var dateCreated = recoveredVehicle.getCreated();
        BeanUtils.copyProperties(vehicle, recoveredVehicle);
        recoveredVehicle.setId(id);
        recoveredVehicle.setCreated(dateCreated);
        recoveredVehicle.setUpdated(LocalDateTime.now());
        this.vehicleRepository.save(recoveredVehicle);
    }

    @Override
    public void delete(Long id) {
        this.vehicleRepository.deleteById(id);
    }

    @Override
    public void sell(Long id) {
        var vehicle = this.findById(id);
        var sold = !vehicle.isSold();
        vehicle.setSold(sold);
        log.info("Update vehicle with id: {} to sold: {}", id, sold);
        this.vehicleRepository.save(vehicle);
    }

    private void validateVehicleWithSameChassis(String chassis){
        var vehicle = vehicleRepository.findByChassis(chassis);
        if (vehicle.isPresent()) throw new VehicleWithSameChassisException();
    }

    private void validateVehicleWithYearGreaterThanCurrentYear(int year){
        var today = LocalDate.now();
        if (year > today.getYear()) throw new VehicleWithInvalidYearException();
    }
}
