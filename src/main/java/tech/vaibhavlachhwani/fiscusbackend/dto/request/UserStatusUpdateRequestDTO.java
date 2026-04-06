package tech.vaibhavlachhwani.fiscusbackend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request payload for toggling user active status")
public class UserStatusUpdateRequestDTO {
    @NotNull(message = "Active status cannot be null")
    @Schema(description = "Set to true to activate or false to deactivate the user", example = "false")
    private Boolean isActive;
}
