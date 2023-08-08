package com.eds.technicalchallenge.repositories;

import com.eds.technicalchallenge.domain.vehicle.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>, VehicleCustomRepository {

    @Query("SELECT v FROM Vehicle v WHERE 1=1 and (:name is null or v.name like %:name% )")
    Page<Vehicle> findAll(String name, Pageable pageable);
    Optional<Vehicle> findByChassis(String chassis);
}
