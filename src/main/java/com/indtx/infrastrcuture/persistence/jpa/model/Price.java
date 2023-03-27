package com.indtx.infrastrcuture.persistence.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@IdClass(PriceId.class)
@Table(name="PRICES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Price {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    private Brand brandId;

    @Id
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name="START_DATE")
    private LocalDateTime startDate;

    @Id
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name="END_DATE")
    private LocalDateTime endDate;

    @Column(name="PRICE_LIST")
    private Integer priceList;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product productId;

    @Id
    @Column(name="PRIORITY")
    private Integer priority;

    @Column(name="PRICE")
    private Double price;

    @Column(name="CURRENCY")
    private String currency;
}
