package com.indtx.infrastrcuture.controller;

import com.indtx.application.price.Search;
import com.indtx.domain.brand.BrandId;
import com.indtx.domain.brand.BrandNotFoundException;
import com.indtx.domain.price.Price;
import com.indtx.domain.product.ProductId;
import com.indtx.domain.product.ProductNotFoundException;
import com.indtx.infrastrcuture.mapper.PriceToResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PriceController {

    private final Search searchService;

    private final PriceToResponseDto mapper;

    @GetMapping("/brands/{brandId}/products/{productId}/prices")
    public ResponseEntity<List<ResponseDto>> getPrice(
            @PathVariable("brandId") Integer brandId,
            @PathVariable("productId") Integer productId,
            @RequestParam("applicationDate")  String applicationDateString) {

        Optional<List<Price>> priceList;
        LocalDateTime applicationDate;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");
            applicationDate = LocalDateTime.parse(applicationDateString, formatter);
        } catch(DateTimeParseException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            priceList =  searchService.search(new BrandId(brandId), new ProductId(productId), applicationDate);
        } catch (BrandNotFoundException | ProductNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (priceList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mapper.mapToDto(priceList), HttpStatus.OK);
        }
    }
}
