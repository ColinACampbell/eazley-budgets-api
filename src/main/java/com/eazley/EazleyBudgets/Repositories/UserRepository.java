package com.eazley.EazleyBudgets.Repositories;

import com.eazley.EazleyBudgets.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmailAndPassword(String email,String password);
    User findByEmail(String email);
    void deleteById(int id);
}
