package tech.vaibhavlachhwani.fiscusbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.DashboardSummaryResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.ErrorResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Endpoints for high-level financial data aggregation and analytics")
public class DashboardController {
    private final DashboardService dashboardService;

    @Operation(
            summary = "Get Business Summary",
            description = "Provides a complete financial overview including total income/expenses, net balance, current month analytics, and recent activity logs.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dashboard data retrieved successfully",
                            content = @Content(schema = @Schema(implementation = DashboardSummaryResponseDTO.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden - Insufficient permissions",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
            }
    )
    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryResponseDTO> getBusinessSummary() {
        DashboardSummaryResponseDTO summary = dashboardService.getSummary();
        return ResponseEntity.ok(summary);
    }
}
