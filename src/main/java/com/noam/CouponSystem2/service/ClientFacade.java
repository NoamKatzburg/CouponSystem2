package com.noam.CouponSystem2.service;

import java.sql.SQLException;

import com.noam.CouponSystem2.repo.CompanyRepository;
import com.noam.CouponSystem2.repo.CouponRepository;
import com.noam.CouponSystem2.repo.CustomerRepository;


public abstract class ClientFacade {

	protected CompanyRepository companiesDAO;
	protected CustomerRepository customersDAO;
	protected CouponRepository couponsDAO;
	
	public ClientFacade() {
//		companiesDAO = new com
//		customersDAO = new CustomersDBDAO();
//		couponsDAO = new CouponsDBDAO();
	}


	public abstract boolean login(String email, String password)  throws SQLException;
	
}
