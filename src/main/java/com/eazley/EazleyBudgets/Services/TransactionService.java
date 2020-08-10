package com.eazley.EazleyBudgets.Services;

import com.eazley.EazleyBudgets.Models.Account;
import com.eazley.EazleyBudgets.Models.Transaction;

import com.eazley.EazleyBudgets.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    public List<Transaction> getAll()
    {
        return (List<Transaction>) transactionRepository.findAll();
    }

    // Create transaction when new account is created
    public void createTransaction(Account account, double amount)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setDate(dateFormat.format(date));
        transaction.setTime(timeFormat.format(date));
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
    }

    // Create transaction to update new account
    public void createTransaction(int accountID, double amount, String accountType)
    {
        if (accountType.equals("DEBIT"))
            amount *= -1; // make it negative

        Account account = accountService.getAccount(accountID);
        System.out.println(account.getId());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setDate(dateFormat.format(date));
        transaction.setTime(timeFormat.format(date));
        transaction.setAmount(amount);

        account.setFunds(amount + account.getFunds());

        transactionRepository.save(transaction);
    }

    // Get Transaction from account id
    public List<Transaction> getTransactions(int accountID)
    {
        Account account = accountService.getAccount(accountID);
        return transactionRepository.findByAccount(account);
    }

    @Transactional
    public void deleteTransactions(int accountID)
    {
        Account account = accountService.getAccount(accountID);
        transactionRepository.deleteByAccount(account);

    }
}
