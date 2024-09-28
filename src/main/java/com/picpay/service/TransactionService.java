package com.picpay.service;

import com.picpay.domain.transaction.Transaction;
import com.picpay.domain.transaction.TransactionDTO;
import com.picpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;

    public List<Transaction> getTransactions(){
        return repository.findAll();

    }
    public TransactionDTO createTransaction(TransactionDTO transactionDTO){
        userService.updateUserWithTransaction(transactionDTO);
        Transaction transaction = new Transaction(transactionDTO.transferValue(), transactionDTO.sender(), transactionDTO.receiver());
        repository.save(transaction);
        return transactionDTO;
    }

}