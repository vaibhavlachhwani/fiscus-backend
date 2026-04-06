package tech.vaibhavlachhwani.fiscusbackend.service;

import org.springframework.data.domain.Page;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.FinancialRecordRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.FinancialRecordResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.entity.FinancialRecord;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordService {
    FinancialRecordResponseDTO createRecord(FinancialRecordRequestDTO record);
    Page<FinancialRecordResponseDTO> getFilteredRecords(
            tech.vaibhavlachhwani.fiscusbackend.enums.TransactionType type,
            String category,
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size);
    FinancialRecordResponseDTO findById(Long id);
    FinancialRecordResponseDTO updateRecord(Long id, FinancialRecordRequestDTO record);
    void deleteRecord(Long id);
}
