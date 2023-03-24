package com.indtx.test.application.price;

import com.indtx.test.domain.brand.BrandId;
import com.indtx.test.domain.price.Price;
import com.indtx.test.domain.price.PriceRepository;
import com.indtx.test.domain.product.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Search {
    private final PriceRepository repository;

    public Optional<List<Price>> search(BrandId brandId, ProductId productId, LocalDateTime applicationDate) {
        return repository.search(brandId, productId, applicationDate);
    }
}
