package com.noam.CouponSystem2.clr;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.service.CompanyFacade;
import com.noam.CouponSystem2.service.CustomerFacade;


@Component
public class Test implements CommandLineRunner{

	@Autowired
	private CompanyFacade comService;
	@Autowired
	private CustomerFacade custService;
//	@Autowired
//	private AdminFacade adminService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("start CompanyClr");
		Company com1 = new Company();
		com1.setName("company1");
		com1.setEmail("com1@g.com");
		com1.setPassword("com1");

		Company com2 = new Company();
		com2.setName("company2");
		com2.setEmail("com2@g.com");
		com2.setPassword("com2");

		Company com3 = new Company();
		com3.setName("company3");
		com3.setEmail("com3@g.com");
		com3.setPassword("com3");
		
		
		
		
		Customer cust1 = new Customer();
		cust1.setFirstName("customer1");
		cust1.setLastName("customer");
		cust1.setEmail("cust1@g.com");
		cust1.setPassword("1234");

		Customer cust2 = new Customer();
		cust2.setFirstName("customer2");
		cust2.setLastName("customer");
		cust2.setEmail("cust2@g.com");
		cust2.setPassword("1234");

		Customer cust3 = new Customer();
		cust3.setFirstName("customer3");
		cust3.setLastName("customer");
		cust3.setEmail("cust3@g.com");
		cust3.setPassword("1234");

		Customer cust4 = new Customer();
		cust4.setFirstName("customer4");
		cust4.setLastName("customer");
		cust4.setEmail("cust4@g.com");
		cust4.setPassword("1234");

		Coupon coupon1 = new Coupon();
		coupon1.setCompanyId(1);
		coupon1.setCategory(Category.ELECTRICITY);
		coupon1.setTitle("new");
		coupon1.setDescription("bolts and more");
		String start1 = "2021-09-03";
		String end1 = "2021-09-17";
		coupon1.setStartDate(java.sql.Date.valueOf(start1));
		coupon1.setEndDate(java.sql.Date.valueOf(end1));
		coupon1.setAmount(21);
		coupon1.setPrice(22.2);
		coupon1.setImage("img");
		

		Coupon coupon2 = new Coupon();
		coupon2.setCompanyId(2);
		coupon2.setCategory(Category.FOOD);
		coupon2.setTitle("1+1");
		coupon2.setDescription("eat more");
		String start2 = "2021-09-17";
		String end2 = "2021-09-17";
		coupon2.setStartDate(java.sql.Date.valueOf(start2));
		coupon2.setEndDate(java.sql.Date.valueOf(end2));
		coupon2.setAmount(100);
		coupon2.setPrice(51.5);
		coupon2.setImage("img");

		Coupon coupon3 = new Coupon();
		coupon3.setCompanyId(3);
		coupon3.setCategory(Category.RESTAURANT);
		coupon3.setTitle("come to us");
		coupon3.setDescription("free meal");
		String start3 = "2019-09-17";
		String end3 = "2019-09-17";
		coupon3.setStartDate(java.sql.Date.valueOf(start3));
		coupon3.setEndDate(java.sql.Date.valueOf(end3));
		coupon3.setAmount(33);
		coupon3.setPrice(221.5);
		coupon3.setImage("img");

		Coupon coupon4 = new Coupon();
		coupon4.setCompanyId(1);
		coupon4.setCategory(Category.RESTAURANT);
		coupon4.setTitle("foood");
		coupon4.setDescription("hello");
		String start4 = "2019-09-03";
		String end4 = "2019-09-17";
		coupon4.setStartDate(java.sql.Date.valueOf(start4));
		coupon4.setEndDate(java.sql.Date.valueOf(end4));
		coupon4.setAmount(1213);
		coupon4.setPrice(66.9);
		coupon4.setImage("img");
		
//		comService.addCompany(com1);
//		comService.addCompany(com2);
//		comService.addCompany(com3);
	
		
	System.out.println("end CLR");}

}
