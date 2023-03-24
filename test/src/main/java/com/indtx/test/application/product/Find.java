package com.indtx.test.application.product;

import com.indtx.test.domain.product.Product;
import com.indtx.test.domain.product.ProductId;
import com.indtx.test.domain.product.ProductNotFoundException;
import com.indtx.test.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("ProductFinder")
@RequiredArgsConstructor
public final class Find {
    private final ProductRepository repository;

    public Product find(ProductId id) {
        return repository.find(id).orElseThrow(() -> new ProductNotFoundException("Product not exists"));
    }
}

