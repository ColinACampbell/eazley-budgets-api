package com.eazley.EazleyBudgets.Services;

import com.eazley.EazleyBudgets.Models.Account;
import com.eazley.EazleyBudgets.Models.User;
import com.eazley.EazleyBudgets.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionService transactionService;

    // Creates an account
    public void createAccount(User owner, String name,String description,double funds, String type)
    {
        Account account = new Account();
        account.setAccountName(name);
        account.setAccountType(type);
        account.setFunds(funds);
        account.setDescription(description);
        account.setUser(owner);

        accountRepository.save(account);

        transactionService.createTransaction(account,account.getFunds());
    }

    // Gets all accounts for a user
    public List<Account> getAccounts(User owner)
    {
        return accountRepository.findByUser(owner);
    }

    public Account getAccount(int id)
    {
        return accountRepository.findById(id);
    }

    public void deleteAccount(int id)
    {
        try {
            accountRepository.deleteById(id);
            transactionService.deleteTransactions(id);
        } catch (EmptyResultDataAccessException e)
        {
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteAccounts(User user)
    {
        accountRepository.deleteByUser(user);
    }


}
