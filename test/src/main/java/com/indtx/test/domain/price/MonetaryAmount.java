package com.indtx.test.domain.price;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MonetaryAmount {
    private final double value;

    private final String currency;
}
