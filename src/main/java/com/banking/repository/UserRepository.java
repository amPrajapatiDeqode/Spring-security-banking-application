package com.banking.repository;

import com.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("UPDATE User SET balance = :balance WHERE userId = :userId")
    void updateBalanceByUserId(@Param("balance") int balance, @Param("userId") long userId);

}
