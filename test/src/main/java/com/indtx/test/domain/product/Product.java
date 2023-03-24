package com.indtx.test.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class Product {
    private final ProductId id;

    private final String description;
}
