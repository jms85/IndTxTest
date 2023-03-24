package com.indtx.test.infrastrcuture.persistence.jpa.repository;

import com.indtx.test.infrastrcuture.persistence.jpa.model.Brand;
import com.indtx.test.infrastrcuture.persistence.jpa.model.Price;
import com.indtx.test.infrastrcuture.persistence.jpa.model.PriceId;
import com.indtx.test.infrastrcuture.persistence.jpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, PriceId> {
    Optional<List<Price>> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateIsGreaterThanEqual(Brand brand, Product product, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
