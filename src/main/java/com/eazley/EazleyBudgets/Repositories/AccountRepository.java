package com.eazley.EazleyBudgets.Repositories;

import com.eazley.EazleyBudgets.Models.Account;
import com.eazley.EazleyBudgets.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {
    List<Account> findByUser(User user);
    Account findById(int id);
    long deleteById(int id);
}
