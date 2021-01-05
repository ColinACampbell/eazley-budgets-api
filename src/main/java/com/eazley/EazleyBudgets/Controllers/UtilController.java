package com.eazley.EazleyBudgets.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/util")
public class UtilController {
    @GetMapping("wake-service")
    public ResponseEntity<Void> wakeService()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
