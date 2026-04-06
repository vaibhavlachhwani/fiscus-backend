package tech.vaibhavlachhwani.fiscusbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatusUpdateRequestDTO {
    @NotNull(message = "Active status cannot be null")
    private Boolean isActive;
}
