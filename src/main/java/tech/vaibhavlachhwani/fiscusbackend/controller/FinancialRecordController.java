package tech.vaibhavlachhwani.fiscusbackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.FinancialRecordRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.FinancialRecordResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType;
import tech.vaibhavlachhwani.fiscusbackend.service.FinancialRecordService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/records")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class FinancialRecordController {
    private final FinancialRecordService recordService;

    @PostMapping
    public ResponseEntity<FinancialRecordResponseDTO> createRecord(@Valid @RequestBody FinancialRecordRequestDTO recordDto) {
        FinancialRecordResponseDTO response = recordService.createRecord(recordDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<FinancialRecordResponseDTO>> getAllRecords(
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<FinancialRecordResponseDTO> response = recordService
                .getFilteredRecords(type, category, startDate, endDate, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialRecordResponseDTO> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(recordService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialRecordResponseDTO> updateRecord(
            @PathVariable Long id,
            @Valid @RequestBody FinancialRecordRequestDTO request) {
        FinancialRecordResponseDTO updatedRecord = recordService.updateRecord(id, request);
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
