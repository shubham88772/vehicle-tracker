package com.vehicle.tracker.cox.repository;

import com.vehicle.tracker.cox.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vehicle.tracker.cox.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    @Query("FROM Customer c WHERE c.mobileNumber=?1")
    public Customer findCustomerByMobile(String mobileNumber);

}
