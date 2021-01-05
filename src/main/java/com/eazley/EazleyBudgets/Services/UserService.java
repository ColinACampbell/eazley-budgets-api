package com.eazley.EazleyBudgets.Services;

import com.eazley.EazleyBudgets.Models.User;
import com.eazley.EazleyBudgets.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        user.setPassword(hashPassword(password));
        user.setDateJoined(dateFormat.format(date));

        return userRepository.save(user);
    }

    // TODO : Find a work around for proper hashing
    public User getUser(String email,String password)
    {
        return userRepository.findByEmailAndPassword(email,hashPassword(password));
    }

    public User getUser(String email)
    {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(User user)
    {
        userRepository.deleteById(user.getId());
    }

    public String hashPassword(String password)
    {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return null;
    }

}
