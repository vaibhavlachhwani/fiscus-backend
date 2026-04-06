package tech.vaibhavlachhwani.fiscusbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.FinancialRecordRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.ErrorResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.FinancialRecordResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;
import tech.vaibhavlachhwani.fiscusbackend.service.FinancialRecordService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/records")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@Tag(name = "Financial Records", description = "Endpoints for managing financial transactions (Income/Expenses)")
public class FinancialRecordController {
    private final FinancialRecordService recordService;

    @Operation(
            summary = "Create Financial Record",
            description = "Registers a new income or expense entry in the system. Requires ADMIN role.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Record created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
            }
    )
    @PostMapping
    public ResponseEntity<FinancialRecordResponseDTO> createRecord(@Valid @RequestBody FinancialRecordRequestDTO recordDto) {
        FinancialRecordResponseDTO response = recordService.createRecord(recordDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Search/Filter Records",
            description = "Retrieves a paginated list of financial records with optional filters for type, category, and date range.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved records")
            }
    )
    @GetMapping
    public ResponseEntity<Page<FinancialRecordResponseDTO>> getAllRecords(
            @Parameter(description = "Filter by transaction type") @RequestParam(required = false) TransactionType type,
            @Parameter(description = "Filter by category name") @RequestParam(required = false) String category,
            @Parameter(description = "Starting date (inclusive)") @RequestParam(required = false) LocalDate startDate,
            @Parameter(description = "Ending date (inclusive)") @RequestParam(required = false) LocalDate endDate,
            @Parameter(description = "Zero-based page index") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of records per page") @RequestParam(defaultValue = "10") int size) {

        Page<FinancialRecordResponseDTO> response = recordService
                .getFilteredRecords(type, category, startDate, endDate, page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Record by ID", description = "Fetches details of a single financial record.")
    @GetMapping("/{id}")
    public ResponseEntity<FinancialRecordResponseDTO> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(recordService.findById(id));
    }

    @Operation(summary = "Update Existing Record", description = "Updates transaction details for an existing record. Requires ADMIN role.")
    @PutMapping("/{id}")
    public ResponseEntity<FinancialRecordResponseDTO> updateRecord(
            @PathVariable Long id,
            @Valid @RequestBody FinancialRecordRequestDTO request) {
        FinancialRecordResponseDTO updatedRecord = recordService.updateRecord(id, request);
        return ResponseEntity.ok(updatedRecord);
    }

    @Operation(summary = "Soft Delete Record", description = "Marks a record as deleted without removing it from the database. Requires ADMIN role.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
