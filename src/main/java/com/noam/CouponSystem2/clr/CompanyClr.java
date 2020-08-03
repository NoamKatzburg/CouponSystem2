package com.noam.CouponSystem2.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.service.CompanyFacade;


@Component
public class CompanyClr implements CommandLineRunner{

	@Autowired
	private CompanyFacade service;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("start CompanyClr");
		Company com1 = new Company();
		com1.setName("company1");
		com1.setEmail("com@g.com");
		com1.setPassword("com1");

		Company com2 = new Company();
		com2.setName("company2");
		com2.setEmail("com2@g.com");
		com2.setPassword("1234");

		Company com3 = new Company();
		com3.setName("company3");
		com3.setEmail("com3@g.com");
		com3.setPassword("com3");
		
		
		service.addCompany(com1);
		service.addCompany(com2);
		service.addCompany(com3);
		
		
		com1.setEmail("com1@g.com");
		com2.setPassword("com2");
		
		service.updateCompany(com1);
		service.updateCompany(com2);
		
	System.out.println("end CompanyClr");}

}
