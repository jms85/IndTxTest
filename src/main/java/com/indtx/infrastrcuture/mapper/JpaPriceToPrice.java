package com.indtx.infrastrcuture.mapper;

import com.indtx.domain.brand.BrandId;
import com.indtx.domain.price.DateRange;
import com.indtx.domain.price.MonetaryAmount;
import com.indtx.domain.price.Price;
import com.indtx.domain.price.PriceList;
import com.indtx.domain.product.ProductId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaPriceToPrice {
    public Optional<List<Price>> mapJpaPrice(Optional<List<com.indtx.infrastrcuture.persistence.jpa.model.Price>> jpaPrice) {
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

    private BrandId createBrandId(com.indtx.infrastrcuture.persistence.jpa.model.Price jpaPrice) {
        return new BrandId(jpaPrice.getBrandId().getId());
    }

    private DateRange createDateRange(com.indtx.infrastrcuture.persistence.jpa.model.Price jpaPrice) {
        return new DateRange(jpaPrice.getStartDate(), jpaPrice.getEndDate());
    }

    private PriceList createPriceList(com.indtx.infrastrcuture.persistence.jpa.model.Price jpaPrice) {
        return new PriceList(jpaPrice.getPriceList());
    }

    private ProductId createProductId(com.indtx.infrastrcuture.persistence.jpa.model.Price jpaPrice) {
        return new ProductId(jpaPrice.getProductId().getId());
    }

    private MonetaryAmount createMonetaryAmount(com.indtx.infrastrcuture.persistence.jpa.model.Price jpaPrice) {
        return new MonetaryAmount(jpaPrice.getPrice(), jpaPrice.getCurrency());
    }

}
