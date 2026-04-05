package tech.vaibhavlachhwani.fiscusbackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.DashboardSummaryResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryResponseDTO> getBusinessSummary() {
        DashboardSummaryResponseDTO summary = dashboardService.getSurvivalMetrics();
        return ResponseEntity.ok(summary);
    }
}
