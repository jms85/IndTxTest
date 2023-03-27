package com.indtx.infrastrcuture.persistence.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="BRANDS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BRAND_ID")
    private Integer id;

    @Column(name="NAME")
    private String name;
}
