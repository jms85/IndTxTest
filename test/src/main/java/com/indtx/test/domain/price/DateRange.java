package com.indtx.test.domain.price;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public final class DateRange {
    private final LocalDateTime start;

    private final LocalDateTime end;

    public DateRange(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        ensureCorrectRange();
    }

    private void ensureCorrectRange() {
        if (start.isAfter(end)) {
            throw new DateRangeException("Start date is after the end date");
        }
    }

}
