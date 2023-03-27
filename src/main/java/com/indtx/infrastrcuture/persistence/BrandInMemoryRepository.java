package com.indtx.infrastrcuture.persistence;

import com.indtx.domain.brand.Brand;
import com.indtx.domain.brand.BrandId;
import com.indtx.domain.brand.BrandRepository;
import com.indtx.infrastrcuture.mapper.JpaBrandToBrand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class BrandInMemoryRepository implements BrandRepository {

    private com.indtx.infrastrcuture.persistence.jpa.repository.BrandRepository jpaRepository;

    private JpaBrandToBrand mapper;

    @Override
    public Optional<Brand> find(BrandId brandId) {
        Optional<com.indtx.infrastrcuture.persistence.jpa.model.Brand> jpaBrand = jpaRepository.findById(brandId.getId());

        return mapper.mapJpaBrand(jpaBrand);
    }
}
