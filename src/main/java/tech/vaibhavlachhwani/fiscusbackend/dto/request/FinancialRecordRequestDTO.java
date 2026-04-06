package tech.vaibhavlachhwani.fiscusbackend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "Payload for creating or updating a financial transaction record")
public class FinancialRecordRequestDTO {
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be a positive value")
    @Schema(description = "The transaction amount (must be positive)", example = "1250.75")
    private BigDecimal amount;

    @NotNull(message = "Type (INCOME/EXPENSE) is required")
    @Schema(description = "Nature of the transaction", example = "EXPENSE")
    private TransactionType type;

    @NotBlank(message = "Category is required")
    @Schema(description = "Logical categorization of the record", example = "Software Services")
    private String category;

    @Schema(description = "Optional detailed description or notes", example = "Monthly AWS infrastructure cost")
    private String description;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    @Schema(description = "Date the transaction occurred (ISO format: YYYY-MM-DD)", example = "2024-03-20")
    private LocalDate transactionDate;

    @NotNull(message = "User ID is required")
    @Schema(description = "Owner User ID for this record", example = "1")
    private Long userId;
}
