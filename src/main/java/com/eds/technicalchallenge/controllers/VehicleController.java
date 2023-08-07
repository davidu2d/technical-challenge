package com.eds.technicalchallenge.controllers;

import com.eds.technicalchallenge.domain.vehicle.VehicleCreateDTO;
import com.eds.technicalchallenge.domain.vehicle.VehicleUpdateDTO;
import com.eds.technicalchallenge.services.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vehicles")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false, defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "5") int size){
        var vehicles = this.vehicleService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid VehicleCreateDTO vehicleCreate){
        var vehicle = this.vehicleService.create(vehicleCreate.toVehicle());
        log.info("Create vehicle with chassi: {}", vehicle.getChassis());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        var vehicle = this.vehicleService.findById(id);
        log.info("Find vehicle with id: {}", vehicle.getId());
        return ResponseEntity.ok(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid VehicleUpdateDTO vehicleUpdateDTO){
        this.vehicleService.update(id, vehicleUpdateDTO.toVehicle());
        log.info("Update vehicle with id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/sell")
    public ResponseEntity sell(@PathVariable Long id){
        this.vehicleService.sell(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        this.vehicleService.delete(id);
        log.info("Delete vehicle with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
