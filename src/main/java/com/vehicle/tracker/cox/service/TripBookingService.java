package com.vehicle.tracker.cox.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.vehicle.tracker.cox.exceptions.CustomerException;
import com.vehicle.tracker.cox.exceptions.TripBookingException;
import com.vehicle.tracker.cox.model.TripBooking;

public interface TripBookingService {
  public TripBooking insertTripBooking(String Key,TripBooking tripBooking,Integer customerId) throws TripBookingException,LoginException;
  public TripBooking updateTripBooking(String Key,TripBooking tripBooking) throws TripBookingException,LoginException;
  public TripBooking deleteTripBooking(String Key,Integer tripBookingId) throws TripBookingException,LoginException;
  public List<TripBooking> viewAllTripsByCustomer(String Key, Integer customerId) throws TripBookingException,LoginException;
  public String calculateBill(String Key, Integer customerId, Integer tripBookingId) throws TripBookingException,LoginException,CustomerException;
  
}
