package tech.vaibhavlachhwani.fiscusbackend.service;

import tech.vaibhavlachhwani.fiscusbackend.dto.request.FinancialRecordRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.FinancialRecordResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.entity.FinancialRecord;

import java.util.List;

public interface FinancialRecordService {
    FinancialRecordResponseDTO createRecord(FinancialRecordRequestDTO record);
    List<FinancialRecordResponseDTO> findAll();
    FinancialRecordResponseDTO findById(Long id);
    FinancialRecordResponseDTO updateRecord(Long id, FinancialRecordRequestDTO record);
    void deleteRecord(Long id);
}
