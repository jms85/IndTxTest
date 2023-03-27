package com.indtx.application.product;

import com.indtx.domain.product.Product;
import com.indtx.domain.product.ProductId;
import com.indtx.domain.product.ProductNotFoundException;
import com.indtx.domain.product.ProductRepository;
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

