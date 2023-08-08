package com.eds.technicalchallenge.repositories.impl;

import com.eds.technicalchallenge.domain.vehicle.Vehicle;
import com.eds.technicalchallenge.repositories.VehicleCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class VehicleCustomRepositoryImpl implements VehicleCustomRepository {

    private final EntityManager em;

    @Override
    public Page<Vehicle> findByNameAndBrandAndYearAndPrice(
            String name,
            String brand,
            Integer yearInitial,
            Integer yearFinal,
            BigDecimal priceInitial,
            BigDecimal priceFinal,
            Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);

        Root<Vehicle> vehicleRoot = cq.from(Vehicle.class);
        List<Predicate> predicates = new ArrayList<>();

        if(Strings.isNotEmpty(name)) predicates.add(cb.like(cb.upper(vehicleRoot.get("name")), "%" + name.toUpperCase() + "%"));
        if(Strings.isNotEmpty(brand)) predicates.add(cb.like(cb.upper(vehicleRoot.get("brand")), "%" + brand.toUpperCase() + "%"));

        if(Objects.nonNull(yearInitial) && Objects.isNull(yearFinal)) predicates.add(cb.between(vehicleRoot.get("year"), yearInitial, LocalDate.now().getYear()));
        if(Objects.nonNull(yearFinal) && Objects.isNull(yearInitial)) predicates.add(cb.equal(vehicleRoot.get("year"), yearFinal));
        if(Objects.nonNull(yearInitial) && Objects.nonNull(yearFinal)) predicates.add(cb.between(vehicleRoot.get("year"), yearInitial, yearFinal));

        if(Objects.nonNull(priceInitial) && Objects.isNull(priceFinal)) predicates.add(cb.between(vehicleRoot.get("price"), priceInitial, BigDecimal.valueOf(10000000.00)));
        if(Objects.nonNull(priceFinal) && Objects.isNull(priceInitial)) predicates.add(cb.equal(vehicleRoot.get("price"), priceFinal));
        if(Objects.nonNull(priceInitial) && Objects.nonNull(priceFinal)) predicates.add(cb.between(vehicleRoot.get("price"), priceInitial, priceFinal));

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Vehicle> query = em.createQuery(cq);
        int size = query.getResultList().size();

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return new PageImpl<>(query.getResultList(), pageable, size);
    }
}
