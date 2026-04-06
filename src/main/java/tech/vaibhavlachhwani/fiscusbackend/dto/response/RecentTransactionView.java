package tech.vaibhavlachhwani.fiscusbackend.dto.response;

import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface RecentTransactionView {
    BigDecimal getAmount();
    TransactionType getType();
    String getCategory();
    LocalDate getTransactionDate();
}
