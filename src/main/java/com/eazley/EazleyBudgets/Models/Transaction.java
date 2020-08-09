package com.eazley.EazleyBudgets.Models;

import javax.persistence.*;

// transaction can be an expense or a credit
@Entity
@Table( name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    private String date;
    private String time;
    private double amount;

    @ManyToOne( targetEntity = Account.class)
    Account account;

    public Transaction()
    {

    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
