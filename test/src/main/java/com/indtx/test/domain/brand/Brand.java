package com.indtx.test.domain.brand;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class Brand {
    private final BrandId id;

    private final String name;
}
