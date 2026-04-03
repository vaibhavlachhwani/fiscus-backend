package tech.vaibhavlachhwani.fiscusbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.DashboardSummaryResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;
import tech.vaibhavlachhwani.fiscusbackend.repository.FinancialRecordRepository;
import tech.vaibhavlachhwani.fiscusbackend.service.DashboardService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final FinancialRecordRepository recordRepository;


    @Override
    public DashboardSummaryResponseDTO getSurvivalMetrics() {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);

        // 1. Calculate Current Cash Balance (All-Time Income - All-Time Expenses)
        BigDecimal startingCapital = new BigDecimal("500000.00"); // Assuming a starting capital of 500,000 for realistic startup simulation
        BigDecimal allTimeIncome = recordRepository.getTotalAmountByType(TransactionType.INCOME);
        BigDecimal allTimeExpenses = recordRepository.getTotalAmountByType(TransactionType.EXPENSE);
        BigDecimal currentCashBalance = startingCapital.add(allTimeIncome).subtract(allTimeExpenses);

        // 2. Fetch 30-Day Trailing Metrics
        BigDecimal grossBurnRate = recordRepository.sumExpensesSince(thirtyDaysAgo);
        BigDecimal operationalIncome = recordRepository.sumOperationalIncomeSince(thirtyDaysAgo);
        BigDecimal mrr = recordRepository.calculateMRR(thirtyDaysAgo);

        // 3. Calculate Net Burn Rate (Expenses - Operational Income)
        BigDecimal netBurnRate = grossBurnRate.subtract(operationalIncome);

        // 4. Determine Runway and Profitability
        boolean isProfitable = netBurnRate.compareTo(BigDecimal.ZERO) <= 0;
        BigDecimal runwayMonths = new BigDecimal("-1"); // Default to -1 indicating infinite runway
        LocalDate zeroCashDate = null;

        if (!isProfitable) {
            // Runway = Cash Balance / Net Burn Rate
            // Using RoundingMode.HALF_UP is required when dividing BigDecimals to prevent infinite decimal crashes
            runwayMonths = currentCashBalance.divide(netBurnRate, 2, RoundingMode.HALF_UP);

            // Calculate approximate Zero Cash Date (Current Date + (Runway Months * 30 days))
            int daysUntilZero = runwayMonths.multiply(new BigDecimal("30")).intValue();
            zeroCashDate = LocalDate.now().plusDays(daysUntilZero);
        }

        // 5. Build and return the DTO
        return DashboardSummaryResponseDTO.builder()
                .currentCashBalance(currentCashBalance)
                .grossBurnRate(grossBurnRate)
                .netBurnRate(netBurnRate)
                .mrr(mrr)
                .runwayMonths(runwayMonths)
                .zeroCashDate(zeroCashDate)
                .isProfitable(isProfitable)
                .build();
    }
}
