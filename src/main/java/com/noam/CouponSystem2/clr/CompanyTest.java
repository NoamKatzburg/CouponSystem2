package com.noam.CouponSystem2.clr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.service.CompanyFacade;
import com.noam.CouponSystem2.utils.MyUtils;

@Component
@Order(3)
public class CompanyTest implements CommandLineRunner {

	@Autowired
	private CompanyFacade facade;
	@Autowired
	private AdminFacade admin;
//	private List<Company> companies;
//	private List<Customer> customers;
	private List<Coupon> coupons;

	@Override
	public void run(String... args) throws Exception {
		MyUtils.classSeparator("Company");
		
		MyUtils.printTestLine("Checking login");
		System.out.println("Should return true: "+ facade.login("com1@g.com", "com1"));
		
		MyUtils.printTestLine("Testing add coupon - 5");
		Coupon coupon5 = new Coupon();
		coupon5.setCompanyId(2);
		coupon5.setCategory(Category.VACATION);
		coupon5.setTitle("Hotel");
		coupon5.setDescription("vacation hotel");
		coupon5.setStartDate(java.sql.Date.valueOf("2021-01-03"));
		coupon5.setEndDate(java.sql.Date.valueOf("2021-09-01"));
		coupon5.setAmount(300);
		coupon5.setPrice(10_000);
		coupon5.setImage("img");
		facade.addCoupon(coupon5);
		coupons = admin.getAllCoupons();
		MyUtils.printCouponsTable(coupons);
		
		
		MyUtils.printTestLine("Testing update coupon - 5 set to FOOD");
		coupon5.setCategory(Category.FOOD);
		facade.updateCoupon(coupon5);
		coupons = admin.getAllCoupons();
		MyUtils.printCouponsTable(coupons);
		
		
		MyUtils.printTestLine("Testing delete coupon - 5" );
		facade.deleteCoupon(5);
		coupons = admin.getAllCoupons();
		MyUtils.printCouponsTable(coupons);
		
		
		MyUtils.printTestLine("Testing get company coupons - company 1" );
		coupons = facade.getCompanyCoupons();
		MyUtils.printCouponsTable(coupons);
		
		MyUtils.printTestLine("Testing get company coupons by category ELECTRICTY - company 1" );
		coupons = facade.getCompanyCouponsByCategory(Category.ELECTRICITY);
		MyUtils.printCouponsTable(coupons);
		
		
		MyUtils.printTestLine("Testing get company coupons Max price 50 - company 1" );
		coupons = facade.getCompanyCouponsByPrice(50);
		MyUtils.printCouponsTable(coupons);
		
		
		MyUtils.printTestLine("Testing get company details - company 1" );
		System.out.println(facade.getCompanyDetails());
		
		
	}

}
