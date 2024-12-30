package com.vehicle.tracker.cox.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vehicle.tracker.cox.exceptions.CustomerException;
import com.vehicle.tracker.cox.exceptions.TripBookingException;
import com.vehicle.tracker.cox.model.Cab;
import com.vehicle.tracker.cox.model.CurrentUserSession;
import com.vehicle.tracker.cox.model.Customer;
import com.vehicle.tracker.cox.model.Driver;
import com.vehicle.tracker.cox.model.TripBooking;
import com.vehicle.tracker.cox.repository.CurrentSessionRepo;
import com.vehicle.tracker.cox.repository.CustomerRepo;
import com.vehicle.tracker.cox.repository.TripBookingRepo;

@Service
public class TripBookingServiceImpl implements TripBookingService{
	
	@Autowired
	private TripBookingRepo tripBookingRepo;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Autowired
	private CustomerRepo customerRepo;



	/*-------------------------------- Insert Trip Implementation ---------------------------------*/
	@Override
	public TripBooking insertTripBooking(String key, TripBooking tripBooking,Integer customerId) throws TripBookingException,LoginException{

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession==null) {
			throw new LoginException("No User Login");
			
		}
		else {
			Optional<Customer> customer = customerRepo.findById(customerId);
			
			if(customer.isPresent()) {

				Customer cus=customer.get();
				List<TripBooking>bookings =customer.get().getTripBookings();

				if(bookings.size()>0) {

					TripBooking tr=bookings.get(bookings.size()-1);

					if(bookings.size()>0) {

						if(tr.getStatus()==false) {
							throw new TripBookingException("Cannot Book another ... As trip is already in Progress");
						}
					}

					tripBooking.setCustomer(tr.getCustomer());
					tripBookingRepo.save(tripBooking);

					return tripBooking;
				}
				else {
					tripBooking.setCustomer(cus);
					tripBookingRepo.save(tripBooking);
				}
			}

			return tripBooking;
		}

	}


	/*-------------------------------- Update Trip Implementation ---------------------------------*/
	@Override
	public TripBooking updateTripBooking(String key,TripBooking tripBooking) throws TripBookingException,LoginException {

		CurrentUserSession currentUserSession=currentSessionRepo.findByUuid(key);
		if(currentUserSession==null) {
			throw new LoginException("No User Login");
			
		}
		else {

			Optional<TripBooking> optional= tripBookingRepo.findById(tripBooking.getTripBookingId());
			if(optional.isPresent()) {
				return tripBookingRepo.save(tripBooking);
			}
			else {
				throw new TripBookingException("No Trip Booked with this id: "+tripBooking.getTripBookingId());
			}
		}
	}


	/*-------------------------------- Delete Trip Implementation ---------------------------------*/
	@Override
	public TripBooking deleteTripBooking(String key,Integer tripBookingId) throws TripBookingException,LoginException {

		CurrentUserSession currentUserSession=currentSessionRepo.findByUuid(key);
		if(currentUserSession==null) {
			throw new LoginException("No User Login");
			
		}
		else {

			Optional<TripBooking> optional= tripBookingRepo.findById(tripBookingId);
			if(optional.isPresent()) {
				TripBooking tripBooking = optional.get();
				tripBookingRepo.delete(tripBooking);
				return tripBooking;
			}
			else {
				throw new TripBookingException("Invalid Trip Booking Id "+tripBookingId);
			}

		}
		
	}


	/*-------------------------------- View All Trips Implementation ---------------------------------*/
	@Override
	public List<TripBooking> viewAllTripsByCustomer(String key, Integer customerId) throws TripBookingException,LoginException {

		List<TripBooking> tripBookingList = null;

		CurrentUserSession currentUserSession=currentSessionRepo.findByUuid(key);
		if(currentUserSession==null) {
			throw new LoginException("No User Login");
			
		}
		else {
			Optional<Customer> op = customerRepo.findById(customerId);

			if(op.isPresent()) {
		      tripBookingList = op.get().getTripBookings();
		    }
		}
		if(tripBookingList.isEmpty()) {
			throw new TripBookingException("No trip Found");
		}
		else {
			return tripBookingList;
		}
		
	}


	/*-------------------------------- Generate Trip Bill Implementation ---------------------------------*/
	@Override
	public String calculateBill(String key, Integer customerId, Integer tripBookingId) throws TripBookingException,LoginException,CustomerException {

		TripBooking booking=null;

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession==null) {
			throw new LoginException("No user Login");
		}
		else {
			Optional<TripBooking> optional = tripBookingRepo.findById(tripBookingId);

			if(optional.isPresent()) {

				booking = optional.get();

				if(booking.getStatus()==true) {

					Driver driver=booking.getDriver();
					Cab cab=driver.getCab();

					booking.setDriver(driver);
					booking.setBill(cab.getPerKmRate()*booking.getDistanceInKm());

					tripBookingRepo.save(booking);

				}

			}
			else {
				throw new TripBookingException("Trip Not Found With Id "+tripBookingId);
			}
		}

		return "Total Trip Bill for Trip ID " + booking.getTripBookingId() + " is : " + booking.getBill();

	}

		
}

