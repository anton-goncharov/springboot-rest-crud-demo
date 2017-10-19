package com.antongoncharov.paymentservice.dto.mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DateMapper {

    String asString(LocalDate date) {
        return date != null ? DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date) : null;
    }

    LocalDate asDate(String date) {
        return date != null ? LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(date)) : null;
    }

}
