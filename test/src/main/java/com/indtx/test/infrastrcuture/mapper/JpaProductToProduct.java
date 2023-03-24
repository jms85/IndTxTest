package com.indtx.test.infrastrcuture.mapper;

import com.indtx.test.domain.product.Product;
import com.indtx.test.domain.product.ProductId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaProductToProduct {
    public Optional<Product> mapJpaProduct(Optional<com.indtx.test.infrastrcuture.persistence.jpa.model.Product> jpaProduct) {
        return jpaProduct.map(product -> new Product(new ProductId(product.getId()), product.getDescription()));
    }

    public com.indtx.test.infrastrcuture.persistence.jpa.model.Product mapProduct(Product product) {
        ProductId productId = product.getId();

        return new com.indtx.test.infrastrcuture.persistence.jpa.model.Product(productId.getId(), product.getDescription());
    }
}
