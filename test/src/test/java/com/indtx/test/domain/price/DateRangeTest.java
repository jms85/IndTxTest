package com.indtx.test.domain.price;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class DateRangeTest {

    @Test
    void expectedDateRangeException_whenStartDateAfterEndDate() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().minusDays(1);

        DateRangeException exception = assertThrowsExactly(DateRangeException.class, () -> new DateRange(start, end));

        assertEquals("Start date is after the end date", exception.getMessage());
    }
}