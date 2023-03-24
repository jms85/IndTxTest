package com.indtx.test.domain.brand;

import java.util.Optional;

public interface BrandRepository {
    Optional<Brand> find(BrandId id);
}
