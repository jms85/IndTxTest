package com.indtx.test.domain.price;

import com.indtx.test.domain.brand.BrandId;
import com.indtx.test.domain.product.ProductId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepository {
    Optional<List<Price>> search(BrandId brandId, ProductId productId, LocalDateTime applicationDate);
}
