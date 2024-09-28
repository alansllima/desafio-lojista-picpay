package com.picpay.controller;

import com.picpay.domain.transaction.Transaction;
import com.picpay.domain.transaction.TransactionDTO;
import com.picpay.repository.TransactionRepository;
import com.picpay.service.TransactionService;
import com.picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(){
        return ResponseEntity.ok(transactionService.getTransactions());
    }
    @PostMapping()
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody @Validated TransactionDTO transactionDTO){
        TransactionDTO transaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(transaction);
    }
}
