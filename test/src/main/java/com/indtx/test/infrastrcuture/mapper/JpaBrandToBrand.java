package com.indtx.test.infrastrcuture.mapper;

import com.indtx.test.domain.brand.Brand;
import com.indtx.test.domain.brand.BrandId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaBrandToBrand {
    public Optional<Brand> mapJpaBrand(Optional<com.indtx.test.infrastrcuture.persistence.jpa.model.Brand> jpaBrand) {
        return jpaBrand.map(brand -> new Brand(new BrandId(brand.getId()), brand.getName()));
    }

    public com.indtx.test.infrastrcuture.persistence.jpa.model.Brand mapBrand(Brand brand) {
        BrandId brandId = brand.getId();

        return new com.indtx.test.infrastrcuture.persistence.jpa.model.Brand(brandId.getId(), brand.getName());
    }
}
