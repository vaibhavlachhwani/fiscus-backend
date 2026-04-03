package tech.vaibhavlachhwani.fiscusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.vaibhavlachhwani.fiscusbackend.entity.FinancialRecord;
import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    // 1. All-Time Totals (For calculating Current Cash Balance)
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f WHERE f.type = :type")
    BigDecimal getTotalAmountByType(@Param("type") TransactionType type);

    // 2. Trailing Expenses (For Gross Burn Rate)
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f " +
            "WHERE f.type = 'EXPENSE' AND f.transactionDate >= :startDate")
    BigDecimal sumExpensesSince(@Param("startDate") LocalDate startDate);

    // 3. Trailing Operational Income (For Net Burn Rate - Excluding Investor Funding)
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f " +
            "WHERE f.type = 'INCOME' AND f.category != 'Investor Funding' " +
            "AND f.transactionDate >= :startDate")
    BigDecimal sumOperationalIncomeSince(@Param("startDate") LocalDate startDate);

    // 4. MRR (Subscription Revenue)
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f " +
            "WHERE f.type = 'INCOME' AND f.category = 'Subscription Revenue' " +
            "AND f.transactionDate >= :startDate")
    BigDecimal calculateMRR(@Param("startDate") LocalDate startDate);
}
