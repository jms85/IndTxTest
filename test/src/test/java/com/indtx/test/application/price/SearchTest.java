package com.indtx.test.application.price;

import com.indtx.test.domain.brand.Brand;
import com.indtx.test.domain.brand.BrandId;
import com.indtx.test.domain.price.DateRange;
import com.indtx.test.domain.price.MonetaryAmount;
import com.indtx.test.domain.price.Price;
import com.indtx.test.domain.price.PriceList;
import com.indtx.test.domain.price.PriceRepository;
import com.indtx.test.domain.product.ProductId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchTest {
    @Mock
    private PriceRepository repository;

    @InjectMocks
    private Search search;

    @Test
    void expectedEmptyList_whenPriceNotExists() {
        when(repository.search(any(BrandId.class), any(ProductId.class), any(LocalDateTime.class))).thenReturn(Optional.empty());

        Optional<List<Price>> listPrice = search.search(new BrandId(1), new ProductId(1), LocalDateTime.now());

        assertEquals(Optional.empty(), listPrice);
    }
    @Test
    void expectedPriceList_whenPriceExist() {
        List<Price> priceList = createPriceList();
        when(repository.search(any(BrandId.class), any(ProductId.class), any(LocalDateTime.class))).thenReturn(Optional.of(priceList));

        Optional<List<Price>> actualPriceList =  search.search(new BrandId(1), new ProductId(1), LocalDateTime.now());

        assertEquals(Optional.of(priceList), actualPriceList);
    }

    private List<Price> createPriceList() {

        Price price = new Price(
                new BrandId(1),
                new DateRange(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
                new PriceList(1, "priceList"),
                new ProductId(1),
                1,
                new MonetaryAmount(23, "EUR")
        );

        return List.of(price);
    }

}