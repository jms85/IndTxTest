package com.indtx.test.infrastrcuture.persistence.jpa.repository;

import com.indtx.test.infrastrcuture.persistence.jpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Integer id);
}
