package tech.vaibhavlachhwani.fiscusbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard error response structure for API failures")
public class ErrorResponseDTO {
    @Schema(description = "Time when the error occurred", example = "2024-03-20T15:30:45")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP Status Code", example = "400")
    private int status;

    @Schema(description = "Error category or type", example = "Bad Request")
    private String error;

    @Schema(description = "Detailed error message explaining the failure", example = "One or more fields are invalid")
    private String message;

    @Schema(description = "The endpoint path where the error occurred", example = "/api/records")
    private String path;

    @Schema(description = "Specific validation failures for request fields (only for 400 Bad Request)")
    private Map<String, String> validationErrors;
}
