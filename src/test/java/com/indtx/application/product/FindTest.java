package com.indtx.application.product;

import com.indtx.domain.product.Product;
import com.indtx.domain.product.ProductId;
import com.indtx.domain.product.ProductNotFoundException;
import com.indtx.domain.product.ProductRepository;
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
    private ProductRepository repository;

    @InjectMocks
    private Find find;

    @Test
    void expectedBrandNotFoundException_whenBrandNotExists() {
        when(repository.find(any(ProductId.class))).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrowsExactly(ProductNotFoundException.class, () -> find.find(new ProductId(1)));

        assertEquals("Product not exists", exception.getMessage());
    }

    @Test
    void expectedProduct_whenFindById() {
        Product expectedProduct = new Product(new ProductId(1), "Product1");
        when(repository.find(any(ProductId.class))).thenReturn(Optional.of(expectedProduct));

        Product actualProduct =  find.find(new ProductId(1));

        assertEquals(expectedProduct, actualProduct);
    }
}