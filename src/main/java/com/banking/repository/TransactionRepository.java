package com.banking.repository;

import com.banking.entity.Transaction;
import com.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("Select t from Transaction t where user = :user")
    List<Transaction> findTransactionByUser(User user);
}
