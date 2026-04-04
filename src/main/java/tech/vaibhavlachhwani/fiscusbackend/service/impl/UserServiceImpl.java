package tech.vaibhavlachhwani.fiscusbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.UserRequestDTO;
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
}
