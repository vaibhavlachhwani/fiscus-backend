package tech.vaibhavlachhwani.fiscusbackend.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tech.vaibhavlachhwani.fiscusbackend.entity.FinancialRecord;
import tech.vaibhavlachhwani.fiscusbackend.entity.User;
import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;
import tech.vaibhavlachhwani.fiscusbackend.repository.FinancialRecordRepository;
import tech.vaibhavlachhwani.fiscusbackend.repository.UserRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@Order(2)
@RequiredArgsConstructor
public class FinancialRecordSeeder implements CommandLineRunner {
    private final FinancialRecordRepository financialRecordRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (financialRecordRepository.count() == 0) {
            Optional<User> adminOptional = userRepository.findByEmail("admin@fiscus.com");

            if (adminOptional.isPresent()) {
                User adminUser = adminOptional.get();
                List<FinancialRecord> recordsToSave = new ArrayList<>();

                recordsToSave.add(createRecord(new BigDecimal("500000.00"), TransactionType.INCOME, "Investor Funding", "Seed round funding", LocalDate.now().minusMonths(2), adminUser));
                recordsToSave.add(createRecord(new BigDecimal("15000.00"), TransactionType.INCOME, "Subscription Revenue", "Monthly MRR", LocalDate.now().minusDays(15), adminUser));
                recordsToSave.add(createRecord(new BigDecimal("8000.00"), TransactionType.EXPENSE, "Software Services", "Cloud infrastructure", LocalDate.now().minusDays(10), adminUser));
                recordsToSave.add(createRecord(new BigDecimal("25000.00"), TransactionType.EXPENSE, "Payroll", "Employee salaries", LocalDate.now().minusDays(2), adminUser));

                // Generate 50 random records for pagination testing
                Random random = new Random();
                String[] incomeCategories = {"Subscription Revenue", "Consulting Services", "One-time Sale", "Partner Commission"};
                String[] expenseCategories = {"Payroll", "Software Services", "Marketing", "Office Rent", "Legal Fees", "Travel & Meals"};

                for (int i = 1; i <= 50; i++) {
                    boolean isIncome = random.nextInt(100) > 60; // 40% chance of income, 60% expense
                    TransactionType type = isIncome ? TransactionType.INCOME : TransactionType.EXPENSE;

                    String category = isIncome
                            ? incomeCategories[random.nextInt(incomeCategories.length)]
                            : expenseCategories[random.nextInt(expenseCategories.length)];

                    // Random amount between 100.00 and 10000.00
                    double rawAmount = 100 + (9900 * random.nextDouble());
                    BigDecimal amount = BigDecimal.valueOf(rawAmount).setScale(2, RoundingMode.HALF_UP);

                    // Random date within the last 180 days
                    LocalDate date = LocalDate.now().minusDays(random.nextInt(180));

                    recordsToSave.add(createRecord(amount, type, category, "Automated bulk entry #" + i, date, adminUser));
                }

                // Save all 54 records at once
                financialRecordRepository.saveAll(recordsToSave);
                System.out.println("DATABASE SEEDER: 54 Financial records populated successfully for pagination testing.");
            } else {
                System.out.println("DATABASE SEEDER: Admin user not found. Skipping financial record seeding.");
            }
        } else {
            System.out.println("DATABASE SEEDER: Financial records already exist. Skipping seeding.");
        }
    }
    
    private FinancialRecord createRecord(BigDecimal amount, TransactionType type, String category, String description, LocalDate date, User user) {
        return FinancialRecord.builder()
                .amount(amount)
                .type(type)
                .category(category)
                .description(description)
                .transactionDate(date)
                .user(user)
                .build();
    }
}
