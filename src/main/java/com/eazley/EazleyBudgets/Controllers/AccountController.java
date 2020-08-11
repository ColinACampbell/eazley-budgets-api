package com.eazley.EazleyBudgets.Controllers;

import com.eazley.EazleyBudgets.Models.Account;
import com.eazley.EazleyBudgets.Models.User;
import com.eazley.EazleyBudgets.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity createAccount(HttpSession session, @RequestBody Map<String,Object> payload)
    {
        User user = (User) session.getAttribute("user");

        if (user == null)
        {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        String accountType = (String) payload.get("accountType");
        String accountName = (String) payload.get("accName");
        String accountFunds = (String) payload.get("accFunds");
        String accountDescription = (String) payload.get("accDescription");

        double $accountFunds = Double.parseDouble(accountFunds);

        // checked if its greater than 0 to compensate for user input
        if (accountType.equals("DEBIT") && $accountFunds > 0)
            $accountFunds *= -1; // make it negative

        accountService.createAccount(
                user,accountName,accountDescription,
                $accountFunds,accountType
        );

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Account> getAllAccounts(HttpSession session)
    {
        User user = (User) session.getAttribute("user");

        if (user == null)
            return null;

        return accountService.getAccounts(user);
    }

    @GetMapping("/info/{accID}")
    @ResponseBody
    public Account getAccount(@PathVariable("accID") int accountID, HttpSession session)
    {
        User user = (User) session.getAttribute("user");

        if (user == null)
            return null;

        return accountService.getAccount(accountID);
    }

    @DeleteMapping("/delete/{accID}")
    ResponseEntity deleteAccount(@PathVariable("accID") int accountID, HttpSession session)
    {
        User user = (User) session.getAttribute("user");

        if (user == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        accountService.deleteAccount(accountID);
        return new ResponseEntity(HttpStatus.OK);
    }

}
