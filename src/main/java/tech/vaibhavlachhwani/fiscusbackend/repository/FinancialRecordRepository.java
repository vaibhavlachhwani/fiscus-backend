package tech.vaibhavlachhwani.fiscusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.vaibhavlachhwani.fiscusbackend.entity.FinancialRecord;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
}
