package tech.vaibhavlachhwani.fiscusbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.CategorySum;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.RecentTransactionView;
import tech.vaibhavlachhwani.fiscusbackend.entity.FinancialRecord;
import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f WHERE f.type = :type")
    BigDecimal getTotalAmountByType(@Param("type") TransactionType type);

    @Query("SELECT f.category AS category, SUM(f.amount) AS total FROM FinancialRecord f WHERE f.type = 'INCOME' GROUP BY f.category")
    List<CategorySum> getIncomeByCategory();

    @Query("SELECT f.category AS category, SUM(f.amount) AS total FROM FinancialRecord f WHERE f.type = 'EXPENSE' GROUP BY f.category")
    List<CategorySum> getExpenseByCategory();

    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f WHERE f.type = :type AND f.transactionDate >= :startDate AND f.transactionDate <= :endDate")
    BigDecimal getTotalAmountByTypeAndDateRange(
            @Param("type") TransactionType type,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT f.amount AS amount, f.type AS type, f.category AS category, f.transactionDate AS transactionDate " +
            "FROM FinancialRecord f ORDER BY f.transactionDate DESC, f.createdAt DESC")
    List<RecentTransactionView> getLatestTransactions(Pageable pageable);

    @Query("SELECT f FROM FinancialRecord f WHERE " +
            "(:type IS NULL OR f.type = :type) AND " +
            "(:category IS NULL OR f.category = :category) AND " +
            "(cast(:startDate as date) IS NULL OR f.transactionDate >= :startDate) AND " +
            "(cast(:endDate as date) IS NULL OR f.transactionDate <= :endDate)")
    Page<FinancialRecord> findFilteredRecords(
            @Param("type") TransactionType type,
            @Param("category") String category,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );
}
