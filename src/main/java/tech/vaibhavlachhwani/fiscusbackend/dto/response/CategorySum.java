package tech.vaibhavlachhwani.fiscusbackend.dto.response;

import java.math.BigDecimal;

public interface CategorySum {
    String getCategory();
    BigDecimal getTotal();
}