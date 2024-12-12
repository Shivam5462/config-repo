package com.coforge.finance.service;

import com.coforge.finance.model.Transaction;
import com.coforge.finance.repository.TransactionRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public List<Transaction> findTransactionsByUserId(Long userId) {
		return transactionRepository.findByUserId(userId);
	}

	public List<Transaction> findTransactionsByAdminId(Long adminId) {
		return transactionRepository.findByAdminId(adminId);
	}

	public Optional<Transaction> findTransactionById(Long id) {
		return transactionRepository.findById(id);
	}

	public List<Transaction> findTransactionsByAmountRange(Double minAmount, Double maxAmount) {
		return transactionRepository.findByAmountBetween(minAmount, maxAmount);
	}

	public List<Transaction> findTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		return transactionRepository.findByTimestampBetween(startDate, endDate);
	}

	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public void deleteTransaction(Long transactionId) {
		transactionRepository.deleteById(transactionId);
	}
}
