package com.indtx.test.infrastrcuture.persistence;

import com.indtx.test.domain.product.Product;
import com.indtx.test.domain.product.ProductId;
import com.indtx.test.domain.product.ProductRepository;
import com.indtx.test.infrastrcuture.mapper.JpaProductToProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductInMemoryRepository implements ProductRepository {

    private com.indtx.test.infrastrcuture.persistence.jpa.repository.ProductRepository jpaRepository;

    private JpaProductToProduct mapper;
    @Override
    public Optional<Product> find(ProductId productId) {
        Optional<com.indtx.test.infrastrcuture.persistence.jpa.model.Product> jpaProduct = jpaRepository.findById(productId.getId());

        return mapper.mapJpaProduct(jpaProduct);
    }
}
