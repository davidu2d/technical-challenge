package com.eds.technicalchallenge.services.impl;

import com.eds.technicalchallenge.domain.vehicle.Vehicle;
import com.eds.technicalchallenge.exceptions.*;
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
        validationCreate(vehicle);
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
        validationUpdate(recoveredVehicle);
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

    private void validateVehicleWithSameChassis(String chassis, Notification notification){
        var vehicle = vehicleRepository.findByChassis(chassis);
        if (vehicle.isPresent()) notification.addError("Vehicle already exists with this chassis number!");
    }

    private void validateVehicleWithYearGreaterThanCurrentYear(int year, Notification notification){
        var today = LocalDate.now();
        if (year > today.getYear()) notification.addError("Vehicle year greater than current year!");
    }

    private void validateVehicleWithNegativePrice(BigDecimal price, Notification notification){
        if (price.signum() == -1) notification.addError("Vehicle with negative price");
    }

    private void validationCreate(Vehicle vehicle){
        var notification = new Notification();
        validateVehicleWithSameChassis(vehicle.getChassis(), notification);
        validateVehicleWithYearGreaterThanCurrentYear(vehicle.getYear(), notification);
        validateVehicleWithNegativePrice(vehicle.getPrice(), notification);
        if (notification.hasErrors()) throw new NotificationsException(notification.errorMessage());
    }

    private void validationUpdate(Vehicle vehicle){
        var notification = new Notification();
        validateVehicleWithYearGreaterThanCurrentYear(vehicle.getYear(), notification);
        validateVehicleWithNegativePrice(vehicle.getPrice(), notification);
        if (notification.hasErrors()) throw new NotificationsException(notification.errorMessage());
    }
}
