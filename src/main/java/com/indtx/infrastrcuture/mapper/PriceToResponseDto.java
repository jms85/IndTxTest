package com.indtx.infrastrcuture.mapper;

import com.indtx.domain.price.Price;
import com.indtx.infrastrcuture.controller.ResponseDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PriceToResponseDto {

    public List<ResponseDto> mapToDto(Optional<List<Price>> priceList) {
        return priceList.map(list ->
                list.stream().map(price ->
                        ResponseDto.builder()
                                .brandId(price.getBrandId().getId())
                                .startDate(price.getDateRange().getStart())
                                .endDate(price.getDateRange().getEnd())
                                .productId(price.getProductId().getId())
                                .priceList(price.getPriceList().getId())
                                .currency(price.getAmount().getCurrency())
                                .price(price.getAmount().getValue())
                                .build()).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
