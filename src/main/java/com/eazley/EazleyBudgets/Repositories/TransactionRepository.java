package com.eazley.EazleyBudgets.Repositories;

import com.eazley.EazleyBudgets.Models.Account;
import com.eazley.EazleyBudgets.Models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Integer> {
    List<Transaction> findByAccount(Account account);
    Long deleteByAccount(Account account);
}
