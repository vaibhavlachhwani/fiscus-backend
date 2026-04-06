package tech.vaibhavlachhwani.fiscusbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.AuthRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.AuthResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.ErrorResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.security.JwtService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user identity and access management")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Operation(
            summary = "User Login",
            description = "Authenticates a user with email and password, returning a JWT for secure access.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully authenticated",
                            content = @Content(schema = @Schema(implementation = AuthResponseDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid credentials provided",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }
}
