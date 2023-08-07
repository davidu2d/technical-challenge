package com.eds.technicalchallenge.domain.vehicle;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_vehicle")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "year_vehicle")
    private Integer year;

    @Column(name = "description")
    private String description;

    @Column(name = "is_sold", columnDefinition = "boolean default false")
    private boolean isSold;

    @Column(name = "chassis", unique = true)
    private String chassis;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    public void setPrice(BigDecimal price){
        if (price.signum() == -1)
            this.price = BigDecimal.ZERO;
    }

    public Vehicle(String name, String brand, Integer year, String description, boolean isSold, LocalDateTime created, String chassis, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.description = description;
        this.isSold = isSold;
        this.created = created;
        this.chassis = chassis;
        this.price = price.signum() == -1 ? BigDecimal.ZERO : price;
    }

    public Vehicle(String name, String brand, Integer year, String description, boolean isSold, String chassis, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.description = description;
        this.isSold = isSold;
        this.created = created;
        this.chassis = chassis;
        this.price = price.signum() == -1 ? BigDecimal.ZERO : price;
    }
}
