package com.eazley.EazleyBudgets.Controllers;

import com.eazley.EazleyBudgets.Models.Transaction;
import com.eazley.EazleyBudgets.Models.User;
import com.eazley.EazleyBudgets.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController
{
    @Autowired
    TransactionService transactionService;

    @GetMapping("/all")
    List<Transaction> getAllTransactions(HttpSession session)
    {
        User user = (User) session.getAttribute("user");

        if (user == null)
        {
            return null;
        }
        return  transactionService.getAll();
    }

    @GetMapping("/from-account/{accID}")
    List<Transaction> getTransactionsFromAccount(HttpSession session,
                                                 @PathVariable("accID") int accountID)
    {

        User user = (User) session.getAttribute("user");

        if (user == null)
            return null;

        return transactionService.getTransactions(accountID);
    }

    @PostMapping("/create")
    ResponseEntity createTransaction(HttpSession session, @RequestBody Map<String,Object> payload)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        int accountID = (Integer) payload.get("accountID");
        String amount = (String) payload.get("amount");
        double $amount = Double.valueOf(amount);
        String accountType = (String) payload.get("accountType");

        transactionService.createTransaction(accountID,$amount,accountType);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
