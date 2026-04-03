package tech.vaibhavlachhwani.fiscusbackend.service.impl;

import org.springframework.stereotype.Service;
import tech.vaibhavlachhwani.fiscusbackend.dto.request.FinancialRecordRequestDTO;
import tech.vaibhavlachhwani.fiscusbackend.dto.response.FinancialRecordResponseDTO;
import tech.vaibhavlachhwani.fiscusbackend.entity.FinancialRecord;
import tech.vaibhavlachhwani.fiscusbackend.entity.User;
import tech.vaibhavlachhwani.fiscusbackend.exception.ResourceNotFoundException;
import tech.vaibhavlachhwani.fiscusbackend.repository.FinancialRecordRepository;
import tech.vaibhavlachhwani.fiscusbackend.service.FinancialRecordService;
import tech.vaibhavlachhwani.fiscusbackend.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialRecordServiceImpl implements FinancialRecordService {
    private final FinancialRecordRepository financialRecordRepository;
    private final UserService userService;

    public FinancialRecordServiceImpl(FinancialRecordRepository financialRecordRepository, UserService userService) {
        this.financialRecordRepository = financialRecordRepository;
        this.userService = userService;
    }

    @Override
    public FinancialRecordResponseDTO createRecord(FinancialRecordRequestDTO recordDto) {
        User user = userService.getUserEntityById(recordDto.getUserId());

        FinancialRecord record = FinancialRecord.builder()
                .amount(recordDto.getAmount())
                .type(recordDto.getType())
                .category(recordDto.getCategory())
                .description(recordDto.getDescription())
                .transactionDate(recordDto.getTransactionDate())
                .user(user)
                .build();

        FinancialRecord savedRecord = financialRecordRepository.save(record);

        return FinancialRecordResponseDTO.fromEntity(savedRecord);
    }

    @Override
    public List<FinancialRecordResponseDTO> findAll() {
        return financialRecordRepository.findAll().stream()
                .map(FinancialRecordResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FinancialRecordResponseDTO findById(Long id) {
        FinancialRecord record = getFinancialRecordEntityById(id);
        return FinancialRecordResponseDTO.fromEntity(record);
    }

    @Override
    public FinancialRecordResponseDTO updateRecord(Long id, FinancialRecordRequestDTO recordDto) {
        FinancialRecord existingRecord = getFinancialRecordEntityById(id);

        existingRecord.setAmount(recordDto.getAmount());
        existingRecord.setType(recordDto.getType());
        existingRecord.setCategory(recordDto.getCategory());
        existingRecord.setDescription(recordDto.getDescription());
        existingRecord.setTransactionDate(recordDto.getTransactionDate());

        FinancialRecord updatedRecord = financialRecordRepository.save(existingRecord);
        return FinancialRecordResponseDTO.fromEntity(updatedRecord);
    }

    @Override
    public void deleteRecord(Long id) {
        FinancialRecord existingRecord = getFinancialRecordEntityById(id);
        financialRecordRepository.delete(existingRecord);
    }

    private FinancialRecord getFinancialRecordEntityById(Long id) {
        return financialRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Financial Record with id: " + id + " not found"));
    }
}
