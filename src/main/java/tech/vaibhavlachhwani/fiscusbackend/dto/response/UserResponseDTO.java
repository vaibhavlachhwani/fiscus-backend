package tech.vaibhavlachhwani.fiscusbackend.dto.response;

import lombok.Builder;
import lombok.Data;
import tech.vaibhavlachhwani.fiscusbackend.entity.User;
import tech.vaibhavlachhwani.fiscusbackend.enums.Role;

@Data
@Builder
public class UserResponseDTO {
    private String email;
    private Role role;

    public static UserResponseDTO fromEntity(User user) {
        return UserResponseDTO.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
