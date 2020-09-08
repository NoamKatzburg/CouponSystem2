package com.noam.CouponSystem2.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.dbdao.CompanyDBDAO;
import com.noam.CouponSystem2.dbdao.CouponDBDAO;
import com.noam.CouponSystem2.dbdao.CustomerDBDAO;

@Component
public abstract class ClientFacade {

	@Autowired
	protected CompanyDBDAO companyDBDAO;
	@Autowired
	protected CustomerDBDAO customerDBDAO;
	@Autowired
	protected CouponDBDAO couponDBDAO;

	public abstract boolean login(String email, String password) throws SQLException;

}
