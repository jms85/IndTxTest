package com.indtx.application.brand;

import com.indtx.domain.brand.Brand;
import com.indtx.domain.brand.BrandId;
import com.indtx.domain.brand.BrandNotFoundException;
import com.indtx.domain.brand.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("BrandFinder")
@RequiredArgsConstructor
public final class Find {
    private final BrandRepository repository;

    public Brand find(BrandId id) {
        return repository.find(id).orElseThrow(() -> new BrandNotFoundException("Brand not exists"));
    }
}
