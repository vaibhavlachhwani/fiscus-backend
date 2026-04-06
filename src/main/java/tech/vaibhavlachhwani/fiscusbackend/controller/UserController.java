package tech.vaibhavlachhwani.fiscusbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.UserRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.UserRoleUpdateRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.UserStatusUpdateRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.ErrorResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.UserResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.service.UserService;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Endpoints for managing system users, roles, and account statuses")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Create System User",
            description = "Registers a new user with a specific role and password. Requires ADMIN role.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input or email already exists",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required")
            }
    )
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request) {
        UserResponseDTO response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get User Details", description = "Fetches information for a specific user by their unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Update User Role",
            description = "Changes the access level (Role) of an existing user. Requires ADMIN role.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Role updated successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @PatchMapping("/{id}/role")
    public ResponseEntity<UserResponseDTO> updateUserRole(
            @PathVariable Long id,
            @Valid @RequestBody UserRoleUpdateRequestDTO request) {
        UserResponseDTO updatedUser = userService.updateUserRole(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            summary = "Update User Status",
            description = "Enables or disables (active/inactive) a user account. Requires ADMIN role.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status updated successfully")
            }
    )
    @PatchMapping("/{id}/status")
    public ResponseEntity<UserResponseDTO> updateUserStatus(
            @PathVariable Long id,
            @Valid @RequestBody UserStatusUpdateRequestDTO request) {
        UserResponseDTO updatedUser = userService.updateUserStatus(id, request);
        return ResponseEntity.ok(updatedUser);
    }
}
