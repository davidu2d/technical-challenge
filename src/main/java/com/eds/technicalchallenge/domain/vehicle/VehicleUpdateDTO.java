package com.eds.technicalchallenge.domain.vehicle;

import com.eds.technicalchallenge.annotations.xss.NotXss;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record VehicleUpdateDTO(
        @NotXss @NotBlank String name,
        @NotXss @NotBlank String brand,
        Integer year,
        @NotXss String description,
        boolean isSold,
        @NotXss @NotBlank String chassis,
        BigDecimal price
) {
    public Vehicle toVehicle(){
        return new Vehicle(name, brand, year, description, isSold, chassis, price);
    }
}