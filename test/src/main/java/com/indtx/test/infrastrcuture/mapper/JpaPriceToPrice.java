package com.indtx.test.infrastrcuture.mapper;

import com.indtx.test.domain.brand.BrandId;
import com.indtx.test.domain.price.DateRange;
import com.indtx.test.domain.price.MonetaryAmount;
import com.indtx.test.domain.price.Price;
import com.indtx.test.domain.price.PriceList;
import com.indtx.test.domain.product.ProductId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaPriceToPrice {
    public Optional<List<Price>> mapJpaPrice(Optional<List<com.indtx.test.infrastrcuture.persistence.jpa.model.Price>> jpaPrice) {
        return jpaPrice.map(list ->
                list.stream().map(price -> new Price(
                        createBrandId(price),
                        createDateRange(price),
                        createPriceList(price),
                        createProductId(price),
                        price.getPriority(),
                        createMonetaryAmount(price)
                )).collect(Collectors.toList()));
    }

    private BrandId createBrandId(com.indtx.test.infrastrcuture.persistence.jpa.model.Price jpaPrice) {
        return new BrandId(jpaPrice.getBrandId().getId());
    }

    private DateRange createDateRange(com.indtx.test.infrastrcuture.persistence.jpa.model.Price jpaPrice) {
        return new DateRange(jpaPrice.getStartDate(), jpaPrice.getEndDate());
    }

    private PriceList createPriceList(com.indtx.test.infrastrcuture.persistence.jpa.model.Price jpaPrice) {
        return new PriceList(jpaPrice.getPriceList());
    }

    private ProductId createProductId(com.indtx.test.infrastrcuture.persistence.jpa.model.Price jpaPrice) {
        return new ProductId(jpaPrice.getProductId().getId());
    }

    private MonetaryAmount createMonetaryAmount(com.indtx.test.infrastrcuture.persistence.jpa.model.Price jpaPrice) {
        return new MonetaryAmount(jpaPrice.getPrice(), jpaPrice.getCurrency());
    }

}
