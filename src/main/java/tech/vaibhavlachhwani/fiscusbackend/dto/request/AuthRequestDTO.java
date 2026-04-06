package tech.vaibhavlachhwani.fiscusbackend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Authentication request payload for user login")
public class AuthRequestDTO {
    @Schema(description = "User's registered email address", example = "admin@fiscus.com")
    private String email;

    @Schema(description = "User's password", example = "admin123")
    private String password;
}
