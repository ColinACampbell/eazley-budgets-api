package com.eazley.EazleyBudgets.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "users")
public class User {

    @Id
    @GeneratedValue
    int id;
    String firstName;
    String lastName;
    String email;
    String password;
    String dateJoined;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "user")
    List<Account> accounts;

    public User()
    {

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDateJoined() {
        return dateJoined;
    }


}
