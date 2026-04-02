package tech.vaibhavlachhwani.fiscusbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tech.vaibhavlachhwani.fiscusbackend.enums.Role;

@Data
public class UserRequestDTO {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
    private String password;

    @NotNull(message = "Role must be specified")
    private Role role;
}
