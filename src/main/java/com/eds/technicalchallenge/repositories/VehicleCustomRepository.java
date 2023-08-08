package com.eds.technicalchallenge.repositories;

import com.eds.technicalchallenge.domain.vehicle.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VehicleCustomRepository {
    //List<Vehicle> findByNameAndBrandAndYearAndPrice(String name, String brand, int yearInitial, int yearFinal, BigDecimal priceInitial, BigDecimal priceFinal);
    Page<Vehicle> findByNameAndBrandAndYearAndPrice(String name, String brand, Integer yearInitial, Integer yearFinal, BigDecimal priceInitial, BigDecimal priceFinal, Pageable pageable);
}
