package com.indtx.test.infrastrcuture.persistence;

import com.indtx.test.application.brand.Find;
import com.indtx.test.domain.brand.Brand;
import com.indtx.test.domain.brand.BrandId;
import com.indtx.test.domain.price.Price;
import com.indtx.test.domain.price.PriceRepository;
import com.indtx.test.domain.product.Product;
import com.indtx.test.domain.product.ProductId;
import com.indtx.test.infrastrcuture.mapper.JpaBrandToBrand;
import com.indtx.test.infrastrcuture.mapper.JpaPriceToPrice;
import com.indtx.test.infrastrcuture.mapper.JpaProductToProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PriceInMemoryRepository implements PriceRepository {

    private com.indtx.test.infrastrcuture.persistence.jpa.repository.PriceRepository repository;

    private Find brandFind;

    private com.indtx.test.application.product.Find productFind;

    private JpaBrandToBrand brandMapper;

    private JpaProductToProduct productMapper;

    private JpaPriceToPrice mapper;

    @Override
    public Optional<List<Price>> search(BrandId brandId, ProductId productId, LocalDateTime applicationDate) {

        Brand domainBrand = brandFind.find(brandId);
        Product domainProduct = productFind.find(productId);

        com.indtx.test.infrastrcuture.persistence.jpa.model.Brand jpaBrand = brandMapper.mapBrand(domainBrand);
        com.indtx.test.infrastrcuture.persistence.jpa.model.Product jpaProduct = productMapper.mapProduct(domainProduct);

        return mapper.mapJpaPrice(
                repository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateIsGreaterThanEqual(jpaBrand, jpaProduct, applicationDate, applicationDate)
        );
    }
}
