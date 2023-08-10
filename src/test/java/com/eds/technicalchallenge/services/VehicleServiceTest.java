package com.eds.technicalchallenge.services;

import com.eds.technicalchallenge.domain.vehicle.Vehicle;
import com.eds.technicalchallenge.repositories.VehicleRepository;
import com.eds.technicalchallenge.services.impl.VehicleServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;
    
    private Vehicle vehicle;
    
    @BeforeEach
    public void setup(){
        vehicle = Vehicle.builder()
                .id(1l)
                .name("Carro")
                .brand("Wolks")
                .year(2015)
                .isSold(false)
                .created(LocalDateTime.now())
                .chassis("123456")
                .price(BigDecimal.valueOf(30.000))
                .build();
    }

    @Test
    public void shouldCreateNewVehicle(){
        //cenario
        var vehicle = this.vehicle;
        Mockito.when(this.vehicleRepository.save(vehicle)).thenReturn(vehicle);

        //ação
        Vehicle vehicle1 = vehicleService.create(vehicle);

        //verificação
        Assertions.assertNotNull(vehicle1);
    }

    @Test
    public void notShouldCreateNewVehicleWithChassiDuplicate(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            //cenario
            var vehicle = this.vehicle;
            Mockito.when(this.vehicleRepository.findByChassis(vehicle.getChassis())).thenReturn(Optional.of(vehicle));

            //ação
            vehicleService.create(vehicle);
        });

        //verificacao
        String expectedMessage = "Vehicle already exists with this chassis number!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void notShouldCreateNewVehicleWithYearGreaterThatCurrentYear(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            //cenario
            var vehicle = this.vehicle;
            vehicle.setYear(LocalDate.now().plusYears(2l).getYear());

            //ação
            vehicleService.create(vehicle);
        });

        //verificacao
        String expectedMessage = "Vehicle year greater than current year!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void notShouldCreateNewVehicleWithNegativePrice(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            //cenario
            var vehicle = this.vehicle;
            vehicle.setPrice(BigDecimal.valueOf(-1));
            vehicle.setYear(LocalDate.now().plusYears(2l).getYear());

            //ação
            vehicleService.create(vehicle);
        });

        //verificacao
        String expectedMessage = "Vehicle with negative price";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldSearchAllVehicle(){
        //cenario
        Pageable pageable = PageRequest.of(1, 5);
        Page<Vehicle> vehiclePage = new PageImpl<>(List.of(this.vehicle), pageable, 1);

        Mockito.when(this.vehicleRepository.findByNameAndBrandAndYearAndPrice(null, null, null, null, null, null, pageable)).thenReturn(vehiclePage);

        //ação
        Page<Vehicle> vehicles = vehicleService.findAll(null, null, null, null, null, null, pageable);

        //verificação
        Assertions.assertEquals(vehiclePage.getTotalElements(), vehicles.getTotalElements());
    }

    @Test
    public void shouldSearchAllVehicleWithFilter(){
        //cenario
        Pageable pageable = PageRequest.of(1, 5);
        Page<Vehicle> vehiclePage = new PageImpl<>(List.of(this.vehicle), pageable, 1);

        Mockito.when(this.vehicleRepository.findByNameAndBrandAndYearAndPrice("Carro", null, null, null, null, null, pageable)).thenReturn(vehiclePage);

        //ação
        Page<Vehicle> vehicles = vehicleService.findAll("Carro", null, null, null, null, null, pageable);

        //verificação
        Assertions.assertEquals(vehiclePage.get().findFirst().get(), vehicles.get().findFirst().get());
    }

    @Test
    public void shouldSearchVehicleById(){
        //cenario
        var id = 1l;
        var vehicle = this.vehicle;

        Mockito.when(this.vehicleRepository.findById(id)).thenReturn(Optional.ofNullable(vehicle));

        //ação
        Vehicle vehicle1 = vehicleService.findById(id);

        //verificação
        Assertions.assertNotNull(vehicle1);
    }

    @Test
    public void shouldNotSearchVehicleById(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            //cenario
            var id = 1l;

            //ação
            Vehicle vehicle1 = vehicleService.findById(id);
        });

        //verificacao
        String expectedMessage = "Vehicle not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldUpdateVehicle(){
        //cenario
        var id = 1l;
        var vehicle = this.vehicle;

        Mockito.when(this.vehicleRepository.findById(id)).thenReturn(Optional.ofNullable(vehicle));
        Mockito.when(this.vehicleRepository.save(vehicle)).thenReturn(vehicle);

        //ação
        Vehicle vehicle1 = vehicleService.update(id, vehicle);

        //verificação
        Assertions.assertNotNull(vehicle1);
    }

    @Test
    public void notShouldUpdateVehicleWithYearGreaterThatCurrentYear(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            //cenario
            var id = 1l;
            var vehicle = this.vehicle;
            vehicle.setYear(LocalDate.now().plusYears(2l).getYear());
            Mockito.when(this.vehicleRepository.findById(id)).thenReturn(Optional.ofNullable(vehicle));

            //ação
            vehicleService.update(id, vehicle);
        });

        //verificacao
        String expectedMessage = "Vehicle year greater than current year!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void notShouldUpdateVehicleWithNegativePrice(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            //cenario
            var id = 1l;
            var vehicle = this.vehicle;
            vehicle.setPrice(BigDecimal.valueOf(-1));

            Mockito.when(this.vehicleRepository.findById(id)).thenReturn(Optional.ofNullable(vehicle));

            //ação
            vehicleService.update(id, vehicle);
        });

        //verificacao
        String expectedMessage = "Vehicle with negative price";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldDeleteVehicle(){
        //cenario
        var id = 1l;

        //ação
        vehicleService.delete(id);

        //verificação
        Mockito.verify(this.vehicleRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void shouldSellVehicle(){
        //cenario
        var id = 1l;
        var vehicle = this.vehicle;

        //ação
        Mockito.when(this.vehicleRepository.findById(id)).thenReturn(Optional.ofNullable(vehicle));
        vehicleService.sell(id);

        //verificacao
        Assertions.assertTrue(vehicle.isSold());
    }
}
