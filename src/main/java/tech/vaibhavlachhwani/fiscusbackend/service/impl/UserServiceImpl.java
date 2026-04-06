package tech.vaibhavlachhwani.fiscusbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.UserRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.UserRoleUpdateRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.UserStatusUpdateRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.UserResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.entity.User;
import tech.vaibhavlachhwani.fiscusbackend.exception.ResourceNotFoundException;
import tech.vaibhavlachhwani.fiscusbackend.repository.UserRepository;
import tech.vaibhavlachhwani.fiscusbackend.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userDto) {
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = User.builder()
                .email(userDto.getEmail())
                .password(hashedPassword)
                .role(userDto.getRole())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponseDTO.fromEntity(savedUser);
    }

    @Override
    public UserResponseDTO findById(Long id) {
        User user = getUserEntityById(id);
        return UserResponseDTO.fromEntity(user);
    }

    @Override
    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found."));
    }

    @Override
    public UserResponseDTO updateUserRole(Long id, UserRoleUpdateRequestDTO request) {
        User userToUpdate = getUserEntityById(id);
        validateNotSelfModification(userToUpdate.getEmail(), "demote or change your own role");

        userToUpdate.setRole(request.getRole());
        User updatedUser = userRepository.save(userToUpdate);
        return UserResponseDTO.fromEntity(updatedUser);
    }

    @Override
    public UserResponseDTO updateUserStatus(Long id, UserStatusUpdateRequestDTO request) {
        User userToUpdate = getUserEntityById(id);
        validateNotSelfModification(userToUpdate.getEmail(), "disable your own account");

        userToUpdate.setIsActive(request.getIsActive());
        User updatedUser = userRepository.save(userToUpdate);
        return UserResponseDTO.fromEntity(updatedUser);
    }

    // Private helper to prevent the Genesis Lockout
    private void validateNotSelfModification(String targetUserEmail, String actionDescription) {
        String currentLoggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (targetUserEmail.equals(currentLoggedInEmail)) {
            throw new IllegalStateException("Security Violation: You cannot " + actionDescription + ".");
        }
    }
}
