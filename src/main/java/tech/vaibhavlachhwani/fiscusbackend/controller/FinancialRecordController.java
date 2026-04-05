package tech.vaibhavlachhwani.fiscusbackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.FinancialRecordRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.FinancialRecordResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.service.impl.FinancialRecordServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class FinancialRecordController {
    private final FinancialRecordServiceImpl recordService;

    @PostMapping
    public ResponseEntity<FinancialRecordResponseDTO> createRecord(@Valid @RequestBody FinancialRecordRequestDTO recordDto) {
        FinancialRecordResponseDTO response = recordService.createRecord(recordDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FinancialRecordResponseDTO>> getAllRecords() {
        return ResponseEntity.ok(recordService.findAll());
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
