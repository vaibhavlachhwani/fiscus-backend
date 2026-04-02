package tech.vaibhavlachhwani.fiscusbackend.service.impl;

import org.springframework.stereotype.Service;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.UserRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.UserResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.entity.User;
import tech.vaibhavlachhwani.fiscusbackend.repository.UserRepository;
import tech.vaibhavlachhwani.fiscusbackend.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
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
                .orElseThrow(() -> new RuntimeException("User with id: " + id + " not found."));
    }
}
