package com.eazley.EazleyBudgets.Services;

import com.eazley.EazleyBudgets.Models.User;
import com.eazley.EazleyBudgets.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(String firstName, String lastName,
                           String email, String password)
    {
        // TODO : Make this accessible from a singleton
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setDateJoined(dateFormat.format(date));

        return userRepository.save(user);
    }


    public void createDemoUser()
    {
        User user = new User();
        user.setEmail("prontobol@gmail.com");
        user.setPassword("mypassword");
        user.setFirstName("Colin");
        user.setLastName("Campbell");
        userRepository.save(user);
    }

    // TODO : Find a work around for proper hashing
    public User getUser(String email,String password)
    {
        return userRepository.findByEmailAndPassword(email,password);
    }

    public User getUser(String email)
    {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(User user)
    {
        userRepository.deleteById(user.getId());
    }


}
