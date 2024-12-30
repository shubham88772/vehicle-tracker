package com.vehicle.tracker.cox.model;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tripBookingId;
	private String fromLocation;
	private String toLocation;
	private LocalDate fromDateTime;
	private LocalDate toDateTime;
	private boolean status;
	private float distanceInKm;
	private float bill;

  @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
  
  @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "driverId")
	private Driver driver;
    
	public boolean getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
    
	

}