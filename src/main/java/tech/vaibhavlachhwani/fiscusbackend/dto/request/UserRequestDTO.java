package tech.vaibhavlachhwani.fiscusbackend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tech.vaibhavlachhwani.fiscusbackend.enums.Role;

@Data
@Schema(description = "User creation request payload")
public class UserRequestDTO {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email format")
    @Schema(description = "The unique email address of the user", example = "jane.doe@example.com")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
    @Schema(description = "The user's account password (minimum 8 characters)", example = "SecurePass123")
    private String password;

    @NotNull(message = "Role must be specified")
    @Schema(description = "Assigned system role", example = "ANALYST")
    private Role role;
}
