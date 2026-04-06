package tech.vaibhavlachhwani.fiscusbackend.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Aggregated financial summary for the dashboard view")
public class DashboardSummaryResponseDTO {
    @Schema(description = "Aggregate sum of all income records", example = "1000000.00")
    private BigDecimal totalIncome;

    @Schema(description = "Aggregate sum of all expense records", example = "450000.00")
    private BigDecimal totalExpense;

    @Schema(description = "Net financial position (Income - Expense)", example = "550000.00")
    private BigDecimal netBalance;

    @Schema(description = "Total number of transactions across all categories", example = "1250")
    private long totalTransactionCount;

    @Schema(description = "Income breakdown indexed by category name")
    private Map<String, BigDecimal> incomeByCategory;

    @Schema(description = "Expense breakdown indexed by category name")
    private Map<String, BigDecimal> expenseByCategory;

    @Schema(description = "Income for the current calendar month", example = "15000.00")
    private BigDecimal currentMonthIncome;

    @Schema(description = "Expense for the current calendar month", example = "8000.00")
    private BigDecimal currentMonthExpense;

    @Schema(description = "The 5 most recent financial transactions for activity logs")
    private List<RecentTransactionView> recentTransactions;
}
