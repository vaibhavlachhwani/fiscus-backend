package tech.vaibhavlachhwani.fiscusbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.CategorySum;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.DashboardSummaryResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;
import tech.vaibhavlachhwani.fiscusbackend.repository.FinancialRecordRepository;
import tech.vaibhavlachhwani.fiscusbackend.service.DashboardService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final FinancialRecordRepository financialRecordRepository;

    @Override
    public DashboardSummaryResponseDTO getSurvivalMetrics() {
        BigDecimal totalIncome = financialRecordRepository.getTotalAmountByType(TransactionType.INCOME);
        BigDecimal totalExpense = financialRecordRepository.getTotalAmountByType(TransactionType.EXPENSE);
        BigDecimal netBalance = totalIncome.subtract(totalExpense);

        long totalTransactionCount = financialRecordRepository.count();

        Map<String, BigDecimal> incomeByCategory = financialRecordRepository.getIncomeByCategory()
                .stream()
                .collect(Collectors.toMap(CategorySum::getCategory, CategorySum::getTotal));

        Map<String, BigDecimal> expenseByCategory = financialRecordRepository.getExpenseByCategory()
                .stream()
                .collect(Collectors.toMap(CategorySum::getCategory, CategorySum::getTotal));

        return DashboardSummaryResponseDTO.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .netBalance(netBalance)
                .totalTransactionCount(totalTransactionCount)
                .incomeByCategory(incomeByCategory)
                .expenseByCategory(expenseByCategory)
                .build();
    }
}
