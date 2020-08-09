package com.eazley.EazleyBudgets.Controllers;

import com.eazley.EazleyBudgets.Models.User;
import com.eazley.EazleyBudgets.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    // TODO : Work on this end point
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody Map<String,String> payload,HttpSession session)
    {
        String firstName = payload.get("firstName");
        String lastName = payload.get("lastName");
        String email = payload.get("userEmail");
        String password = payload.get("password");

        if (userService.getUser(email) != null)
            return new ResponseEntity(HttpStatus.CONFLICT);

        User user = userService.createUser(firstName,lastName, email, password);

        System.out.println(user.getEmail());

        session.setAttribute("user",user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticateUser(HttpSession session)
    {
        User user = (User) session.getAttribute("user");

        if (user == null)
        {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } else
        {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    // TODO Refractor this
    @PostMapping("/login")
    public Map<String,String> login(HttpSession httpSession, @RequestBody Map<String,Object> payload)
    {
        String email = (String) payload.get("email");
        String password = (String) payload.get("password");

        User user = userService.getUser(email,password);

        HashMap<String,String> responseBody = new HashMap<>();

        if (user == null)
        {
            responseBody.put("status","denied");
            return responseBody;
        } else
        {
            httpSession.setAttribute("user",user);
            responseBody.put("status","pass");
            return responseBody;
        }
    }

    @GetMapping("/info")
    @ResponseBody
    public User getUserInfo(HttpSession session)
    {
        // User object is stored in the session
        User user = (User) session.getAttribute("user");
        return user;
    }
}
