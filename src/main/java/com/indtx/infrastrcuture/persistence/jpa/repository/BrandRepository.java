package com.indtx.infrastrcuture.persistence.jpa.repository;

import com.indtx.infrastrcuture.persistence.jpa.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Optional<Brand> findById(Integer id);
}
