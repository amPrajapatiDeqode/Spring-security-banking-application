package com.banking.service;

import com.banking.entity.Transaction;
import com.banking.entity.User;
import com.banking.repository.UserRepository;
import com.banking.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String addUser(User user) {
        userRepository.save(user);
        return "User added";
    }

    @Transactional
    public String addTransaction(Transaction transaction, long userId) {
        User user = userRepository.findById(userId).get();

        if (transaction.getAmount() < 0)
            throw new RuntimeException("Please enter valid amount...");

        int balance;
        if (transaction.getTransactionType().equals("deposit")) {
            balance = user.getBalance() + transaction.getAmount();
        } else {
            balance = user.getBalance() - transaction.getAmount();
        }

        if (balance < 0)
            throw new RuntimeException("Low balance can't withdraw money");

        userRepository.updateBalanceByUserId(balance, user.getUserId());
        transactionRepository.save(transaction);
        return "transaction added";
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }


    public List<Transaction> getTransactionsByUserId(long userId) {
        User user = userRepository.findById(userId).get();
        return transactionRepository.findTransactionByUser(user);
    }

    public int getBalanceByUserId(long userId) {
        User user = userRepository.findById(userId).get();
        return user.getBalance();
    }
}
