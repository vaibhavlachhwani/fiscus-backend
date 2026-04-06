package tech.vaibhavlachhwani.fiscusbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.vaibhavlachhwani.fiscusbackend.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleUpdateRequestDTO {
    @NotNull(message = "Role cannot be null")
    private Role role;
}
