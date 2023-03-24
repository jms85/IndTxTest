package com.indtx.test.application.brand;

import com.indtx.test.domain.brand.Brand;
import com.indtx.test.domain.brand.BrandId;
import com.indtx.test.domain.brand.BrandNotFoundException;
import com.indtx.test.domain.brand.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindTest {

    @Mock
    private BrandRepository repository;

    @InjectMocks
    private Find find;

    @Test
    void expectedBrandNotFoundException_whenBrandNotExists() {
        when(repository.find(any(BrandId.class))).thenReturn(Optional.empty());

        BrandNotFoundException exception = assertThrowsExactly(BrandNotFoundException.class, () -> find.find(new BrandId(1)));

        assertEquals("Brand not exists", exception.getMessage());
    }
    @Test
    void expectedBrand_whenFindById() {
        Brand expectedBrand = new Brand(new BrandId(1), "Brand1");
        when(repository.find(any(BrandId.class))).thenReturn(Optional.of(expectedBrand));

        Brand actualBrand =  find.find(new BrandId(1));

        assertEquals(expectedBrand, actualBrand);
    }
}