package com.indtx.test.domain.brand;

import java.util.NoSuchElementException;

public class BrandNotFoundException extends NoSuchElementException {
    public BrandNotFoundException(String message) {
        super(message);
    }
}
