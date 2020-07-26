package com.noam.CouponSystem2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noam.CouponSystem2.beans.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{

		//is company exist
		Customer findByEmailAndPassword(String email, String password);
		
		//get company id by email
		Customer findByEmail(String email);
}
