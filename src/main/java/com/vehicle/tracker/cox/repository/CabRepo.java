package com.vehicle.tracker.cox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicle.tracker.cox.model.Cab;

@Repository
public interface CabRepo extends JpaRepository<Cab, Integer> {

//	public List<Cab> findByAvailbilityStatus(Boolean avalibilityStatus);

}
