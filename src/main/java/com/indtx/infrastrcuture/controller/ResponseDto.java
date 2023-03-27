package com.indtx.infrastrcuture.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseDto implements Serializable {
    private Integer brandId;

    private Integer productId;

    private Integer priceList;

    @JsonFormat(pattern="yyyy-MM-ddHH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern="yyyy-MM-ddHH:mm:ss")
    private LocalDateTime endDate;

    private Double price;

    private String currency;
}
