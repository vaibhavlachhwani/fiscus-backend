package tech.vaibhavlachhwani.fiscusbackend.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import tech.vaibhavlachhwani.fiscusbackend.entity.FinancialRecord;
import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Schema(description = "Detailed representation of a financial transaction record")
public class FinancialRecordResponseDTO {
    @Schema(description = "Unique identifier of the record", example = "101")
    private Long id;

    @Schema(description = "Transaction amount", example = "1500.00")
    private BigDecimal amount;

    @Schema(description = "Type of transaction", example = "INCOME")
    private TransactionType type;

    @Schema(description = "Record category", example = "Subscription Revenue")
    private String category;

    @Schema(description = "Transaction notes", example = "Annual payment for Pro Plan")
    private String description;

    @Schema(description = "Date of transaction", example = "2024-03-15")
    private LocalDate transactionDate;

    @Schema(description = "Email of the user who owns this record", example = "admin@fiscus.com")
    private String userEmail;

    public static FinancialRecordResponseDTO fromEntity(FinancialRecord entity) {
        return FinancialRecordResponseDTO.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .type(entity.getType())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .transactionDate(entity.getTransactionDate())
                .userEmail(entity.getUser().getEmail())
                .build();
    }
}
