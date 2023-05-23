package com.banking.controller;

import com.banking.entity.Transaction;
import com.banking.entity.User;
import com.banking.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankingController {

    @Autowired
    private BankingService bankingService;

    @GetMapping("/user")
    public List<User> getUser() {
        return bankingService.getAllUsers();
    }

    @PostMapping("/add-user")
    public String addUser(@RequestBody User user) {
        return bankingService.addUser(user);
    }

    @PostMapping("/add-transaction/{userId}")
    public String addTransaction(@RequestBody Transaction transaction, @PathVariable("userId") long userId) {
        return bankingService.addTransaction(transaction, userId);
    }

    @GetMapping("/get-transaction")
    public List<Transaction> getTransaction() {
        return bankingService.getTransactions();
    }

    @GetMapping("/get-transaction/{userId}")
    public List<Transaction> getTransaction(@PathVariable("userId") int userId) {
        return bankingService.getTransactionsByUserId(userId);
    }

    @GetMapping("/balance/{userId}")
    public int getBalance(@PathVariable("userId") long userId) {
        return bankingService.getBalanceByUserId(userId);
    }
}
