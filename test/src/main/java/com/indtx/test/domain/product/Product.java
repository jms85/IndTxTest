package com.indtx.test.domain.product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class Product {
    private final ProductId id;

    private final String description;
}
