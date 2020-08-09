package com.eazley.EazleyBudgets.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "accounts" )
public class Account {

    @Id
    @GeneratedValue
    int id;

    @ManyToOne( targetEntity = User.class )
    User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    List<Transaction> transactions;

    String accountName;
    String accountType; // type of account, ie debit or credit
    String description;
    double funds = 0.0;

    public  Account(){}

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public double getFunds() {
        return funds;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getId() {
        return id;
    }

    public String getAccountType() {
        return accountType;
    }

}
