package tech.vaibhavlachhwani.fiscusbackend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.vaibhavlachhwani.fiscusbackend.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request payload for updating user role")
public class UserRoleUpdateRequestDTO {
    @NotNull(message = "Role cannot be null")
    @Schema(description = "The new role to be assigned to the user", example = "ADMIN")
    private Role role;
}
