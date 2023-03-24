package com.indtx.test.domain.price;

import com.indtx.test.domain.brand.BrandId;
import com.indtx.test.domain.product.ProductId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class Price {
    private final BrandId brandId;

    private final DateRange dateRange;

    private final PriceList priceList;

    private final ProductId productId;

    private final Integer priority;

    private final MonetaryAmount amount;
}
