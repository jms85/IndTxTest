package com.indtx.domain.product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> find(ProductId id);
}
