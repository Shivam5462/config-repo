package com.coforge.finance.repository;

import com.coforge.finance.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Long userId);

    List<Transaction> findByAdminId(Long adminId);

    Optional<Transaction> findById(Long id);

    List<Transaction> findByAmountBetween(Double minAmount, Double maxAmount);

    List<Transaction> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
}
