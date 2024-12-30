package com.vehicle.tracker.cox.controller;

import com.vehicle.tracker.cox.exceptions.LogInException;
import com.vehicle.tracker.cox.model.UserLogin;
import com.vehicle.tracker.cox.service.CustomerService;
import com.vehicle.tracker.cox.service.LoginService;
import com.vehicle.tracker.cox.service.CustomerService;
import com.vehicle.tracker.cox.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kapsi")
public class LogInController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CustomerService customerService;

    /*---------------------------------------------  login  ---------------------------------------------*/
    @PostMapping("/login")
    public ResponseEntity<String> loginMapping(@RequestBody UserLogin userLogin) throws LogInException {

        String output = loginService.login(userLogin);
        return new ResponseEntity<String>(output, HttpStatus.OK);

    }


    /*---------------------------------------------  logout   ---------------------------------------------*/
    @PostMapping("/logout")
    public ResponseEntity<String> logoutMapping(@RequestParam String key) throws LogInException {

        String output = loginService.logout(key);
        return new ResponseEntity<String>(output,HttpStatus.OK);

    }
}
