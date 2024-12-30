package com.vehicle.tracker.cox.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.tracker.cox.exceptions.CabException;
import com.vehicle.tracker.cox.exceptions.DriverException;
import com.vehicle.tracker.cox.exceptions.LogInException;
import com.vehicle.tracker.cox.model.Cab;
import com.vehicle.tracker.cox.model.CurrentUserSession;
import com.vehicle.tracker.cox.model.Driver;
import com.vehicle.tracker.cox.repository.CabRepo;
import com.vehicle.tracker.cox.repository.CurrentSessionRepo;
import com.vehicle.tracker.cox.repository.DriverRepo;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	private CabRepo cabRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;


	/*-------------------------------- Add Cab Implementation ---------------------------------*/
	@Override
	public Cab registerCab(Cab cab) throws DriverException {
		
		Cab registerCab = cabRepo.save(cab);
		return registerCab;
	}


	/*-------------------------------- Update Admin Implementation ---------------------------------*/
	@Override
	public Cab updateCab(String key,Integer cabId, Cab cab) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}
		
		Optional<Cab> cabOptional = cabRepo.findById(cabId);
		if(cabOptional.isPresent()) {
			Cab updateCab =  cabOptional.get();
			cabRepo.save(updateCab);
			return updateCab;
		}
		throw new DriverException("Cab Not Found By This Id :" + cabId);
		
	}


	/*-------------------------------- Get All Cabs Implementation ---------------------------------*/
	@Override
	public List<Cab> getAllCabs(String key) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}

		List<Cab> allCabs = cabRepo.findAll();
		if(allCabs.isEmpty()) {
			throw new DriverException("Cabs Data Not Found :");
		}
		
		return allCabs;
	}


	/*-------------------------------- Delete Cab Implementation ---------------------------------*/
	@Override
	public Cab deleteCab(String key,Integer cabId) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}
		
		Optional<Cab> cabOptional = cabRepo.findById(cabId);
		
		if(cabOptional.isPresent()) {
			Cab update = cabOptional.get();
			cabRepo.delete(update);
			return update;
		}
		throw new DriverException("Cab Not Found By This Id :");
	}


	/*-------------------------------- View Cab Implementation ---------------------------------*/
	@Override
	public Driver viewDriverByCabId(String key, Integer cabId) throws CabException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}

		 Optional<Driver> optionalDriver = driverRepo.findById(cabId);
		 if(optionalDriver.isPresent()) {
			 return optionalDriver.get();
		 }
		 throw new CabException("Cab Not Found By This Id :"+cabId);
		
	}


}
