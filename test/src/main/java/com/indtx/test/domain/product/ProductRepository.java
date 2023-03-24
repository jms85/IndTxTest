package com.indtx.test.domain.product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> find(ProductId id);
}
