package com.vehicle.tracker.cox.service;

import java.util.List;

import com.vehicle.tracker.cox.exceptions.CabException;
import com.vehicle.tracker.cox.exceptions.DriverException;
import com.vehicle.tracker.cox.exceptions.LogInException;
import com.vehicle.tracker.cox.model.Cab;
import com.vehicle.tracker.cox.model.Driver;

public interface CabService {

	public Cab registerCab(Cab cab)throws DriverException;
	
	public Cab updateCab(String Key,Integer cabId,Cab cab)throws DriverException,LogInException;
	
	public List<Cab> getAllCabs(String key) throws DriverException, LogInException;
	
	public Cab deleteCab(String Key,Integer cabId)throws DriverException,LogInException;
	
    public Driver viewDriverByCabId(String key, Integer cabId) throws CabException, LogInException;
	
}
