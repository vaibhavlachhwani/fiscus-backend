package tech.vaibhavlachhwani.fiscusbackend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class DashboardSummaryResponseDTO {
    private BigDecimal currentCashBalance;
    private BigDecimal grossBurnRate;
    private BigDecimal netBurnRate;
    private BigDecimal mrr;
    private BigDecimal runwayMonths;
    private LocalDate zeroCashDate;
    private Boolean isProfitable;
}
