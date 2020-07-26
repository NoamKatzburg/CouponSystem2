package com.noam.CouponSystem2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noam.CouponSystem2.beans.Company;


public interface CompanyRepository extends JpaRepository<Company, Integer>{

	//is company exist
	Company findByEmailAndPassword(String email, String password);
	
	//get company id by email
	Company findByEmail(String email);
	
	
	
}
