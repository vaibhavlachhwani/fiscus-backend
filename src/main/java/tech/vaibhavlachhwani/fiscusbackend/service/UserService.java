package tech.vaibhavlachhwani.fiscusbackend.service;

import tech.vaibhavlachhwani.fiscusbackend.dto.request.UserRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.UserResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.entity.User;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO user);
    UserResponseDTO findById(Long id);
    User getUserEntityById(Long id);
}
