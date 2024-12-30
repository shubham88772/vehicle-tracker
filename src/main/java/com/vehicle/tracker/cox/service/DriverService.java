package com.vehicle.tracker.cox.service;

import java.util.List;

import com.vehicle.tracker.cox.exceptions.CabException;
import com.vehicle.tracker.cox.exceptions.DriverException;
import com.vehicle.tracker.cox.exceptions.LogInException;
import com.vehicle.tracker.cox.model.Cab;
import com.vehicle.tracker.cox.model.Driver;

public interface DriverService {

	public Driver registerDriver(Driver driver)throws DriverException;
	
	public Driver updateDriver(String key,Integer driverId,Driver driver)throws DriverException, LogInException;
	
	public Driver getDriverById(String key,Integer driverId)throws DriverException, LogInException;
	
	public Driver getDriverByName(String key, String userName)throws DriverException, LogInException;
	
	public Driver allocateCabToDriver(String key, Integer driverId, Integer cabId) throws DriverException, CabException, LogInException;
	
	public Cab viewCabByDriverId(String key, Integer DriverId) throws DriverException, LogInException;

	public List<Driver> getAllDriver(String key) throws DriverException, LogInException;

	public Driver deleteDriverById(String key, Integer driverId)throws DriverException, LogInException;

}
