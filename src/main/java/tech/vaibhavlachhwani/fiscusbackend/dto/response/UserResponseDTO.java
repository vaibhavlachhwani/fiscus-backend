package tech.vaibhavlachhwani.fiscusbackend.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import tech.vaibhavlachhwani.fiscusbackend.entity.User;
import tech.vaibhavlachhwani.fiscusbackend.enums.Role;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Data transfer object representing a system user")
public class UserResponseDTO {
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "Email address of the user", example = "jane.doe@example.com")
    private String email;

    @Schema(description = "Assigned system role", example = "ADMIN")
    private Role role;

    @Schema(description = "Current account status (active/inactive)", example = "true")
    private Boolean isActive;

    @Schema(description = "Timestamp when the user was created", example = "2024-01-01T12:00:00")
    private LocalDateTime createdAt;

    public static UserResponseDTO fromEntity(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
