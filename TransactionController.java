package com.coforge.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coforge.finance.service.TransactionService;
import com.coforge.finance.model.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/register")
    public ResponseEntity<Transaction> registerTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.findTransactionsByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAdminId(@PathVariable Long adminId) {
        List<Transaction> transactions = transactionService.findTransactionsByAdminId(adminId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.findTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/amount-range/{minAmount}/{maxAmount}")
    public ResponseEntity<List<Transaction>> getTransactionsByAmountRange(@PathVariable Double minAmount, @PathVariable Double maxAmount) {
        List<Transaction> transactions = transactionService.findTransactionsByAmountRange(minAmount, maxAmount);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/date-range/{startDate}/{endDate}")
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(@PathVariable String startDate, @PathVariable String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);
        List<Transaction> transactions = transactionService.findTransactionsByDateRange(start, end);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}
