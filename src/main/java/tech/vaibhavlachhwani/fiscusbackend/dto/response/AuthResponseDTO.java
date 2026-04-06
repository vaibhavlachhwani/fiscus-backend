package tech.vaibhavlachhwani.fiscusbackend.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response payload containing the generated JWT token")
public class AuthResponseDTO {
    @Schema(description = "Signed JSON Web Token for authenticated access", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;
}
