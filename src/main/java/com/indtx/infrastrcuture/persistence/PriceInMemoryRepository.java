package com.indtx.infrastrcuture.persistence;

import com.indtx.application.brand.Find;
import com.indtx.domain.brand.Brand;
import com.indtx.domain.brand.BrandId;
import com.indtx.domain.price.Price;
import com.indtx.domain.price.PriceRepository;
import com.indtx.domain.product.Product;
import com.indtx.domain.product.ProductId;
import com.indtx.infrastrcuture.mapper.JpaBrandToBrand;
import com.indtx.infrastrcuture.mapper.JpaPriceToPrice;
import com.indtx.infrastrcuture.mapper.JpaProductToProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PriceInMemoryRepository implements PriceRepository {

    private com.indtx.infrastrcuture.persistence.jpa.repository.PriceRepository repository;

    private Find brandFind;

    private com.indtx.application.product.Find productFind;

    private JpaBrandToBrand brandMapper;

    private JpaProductToProduct productMapper;

    private JpaPriceToPrice mapper;

    @Override
    public Optional<List<Price>> search(BrandId brandId, ProductId productId, LocalDateTime applicationDate) {

        Brand domainBrand = brandFind.find(brandId);
        Product domainProduct = productFind.find(productId);

        com.indtx.infrastrcuture.persistence.jpa.model.Brand jpaBrand = brandMapper.mapBrand(domainBrand);
        com.indtx.infrastrcuture.persistence.jpa.model.Product jpaProduct = productMapper.mapProduct(domainProduct);

        return mapper.mapJpaPrice(
                repository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateIsGreaterThanEqual(jpaBrand, jpaProduct, applicationDate, applicationDate)
        );
    }
}
