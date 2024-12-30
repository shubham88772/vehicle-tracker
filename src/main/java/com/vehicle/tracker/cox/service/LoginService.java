package com.vehicle.tracker.cox.service;

import com.vehicle.tracker.cox.exceptions.LogInException;
import com.vehicle.tracker.cox.model.Customer;
import com.vehicle.tracker.cox.model.UserLogin;


public interface LoginService {

    public String login (UserLogin userLogin) throws LogInException;

    public String logout (String Key) throws LogInException;

}
