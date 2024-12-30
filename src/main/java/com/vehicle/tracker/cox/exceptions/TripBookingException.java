package com.vehicle.tracker.cox.exceptions;

public class TripBookingException extends RuntimeException{

    public TripBookingException() {
    }

    public TripBookingException(String message) {
        super(message);
    }
}
