package com.indtx.test.domain.price;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class MonetaryAmount {
    private final double value;

    private final String currency;
}
