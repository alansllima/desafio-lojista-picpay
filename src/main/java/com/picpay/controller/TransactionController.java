package com.picpay.controller;

import com.picpay.domain.transaction.Transaction;
import com.picpay.domain.transaction.TransactionDTO;
import com.picpay.repository.TransactionRepository;
import com.picpay.service.TransactionService;
import com.picpay.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@Tag(name = "Exemplo transactions",description = "description teste")
public class TransactionController {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    @Operation(summary = "get all Transactions")
    public ResponseEntity<List<Transaction>> getTransactions(){
        return ResponseEntity.ok(transactionService.getTransactions());
    }
    @PostMapping()
    @Operation(summary = "send new Transaction")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody @Validated TransactionDTO transactionDTO){
        TransactionDTO transaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(transaction);
    }
}
