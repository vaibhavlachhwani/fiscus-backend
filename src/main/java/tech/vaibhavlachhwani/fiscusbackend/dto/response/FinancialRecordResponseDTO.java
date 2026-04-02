package tech.vaibhavlachhwani.fiscusbackend.dto.response;

import lombok.Builder;
import lombok.Data;
import tech.vaibhavlachhwani.fiscusbackend.entity.FinancialRecord;
import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FinancialRecordResponseDTO {
    private Long id;
    private BigDecimal amount;
    private TransactionType type;
    private String category;
    private String description;
    private LocalDate transactionDate;
    private String userEmail; // Flattening the User entity for the frontend

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
