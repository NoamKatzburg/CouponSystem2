package com.noam.CouponSystem2.clr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.login.ClientType;
import com.noam.CouponSystem2.login.LoginManager;
import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.service.ClientFacade;
import com.noam.CouponSystem2.service.CompanyFacade;
import com.noam.CouponSystem2.utils.PrintUtils;

@Component
@Order(3)
public class CompanyTest implements CommandLineRunner {

	@Autowired
	private AdminFacade admin;
	@Autowired
	private ClientFacade companyFacade;
	@Autowired
	private LoginManager loginManager;
	private List<Coupon> coupons;

	@Override
	public void run(String... args) throws Exception {
		PrintUtils.classSeparator("Company");

		PrintUtils.printTestLine("Checking login");
		companyFacade = loginManager.login("com2@g.com", "com2345", ClientType.COMPANY);
		
		PrintUtils.printTestLine("Testing add coupon - 6");
		Coupon coupon6 = new Coupon();
		coupon6.setCompanyId(2);
		coupon6.setCategory(Category.VACATION);
		coupon6.setTitle("Hotel");
		coupon6.setDescription("vacation hotel");
		coupon6.setStartDate(java.sql.Date.valueOf("2021-01-03"));
		coupon6.setEndDate(java.sql.Date.valueOf("2021-09-01"));
		coupon6.setAmount(300);
		coupon6.setPrice(10_000);
		coupon6.setImage("img");
		((CompanyFacade) companyFacade).addCoupon(coupon6);
		coupons = admin.getAllCoupons();
		PrintUtils.printCouponsTable(coupons);

		PrintUtils.printTestLine("Testing  bad update coupon - 6 set company id from 2 to 3");
		coupon6.setCompanyId(3);
		((CompanyFacade) companyFacade).updateCoupon(coupon6);
		coupons = admin.getAllCoupons();
		PrintUtils.printCouponsTable(coupons);

		PrintUtils.printTestLine("Testing update coupon - 6 set to FOOD");
		coupon6.setCategory(Category.FOOD);
		((CompanyFacade) companyFacade).updateCoupon(coupon6);
		coupons = admin.getAllCoupons();
		PrintUtils.printCouponsTable(coupons);

		PrintUtils.printTestLine("Testing delete coupon - 6");
		((CompanyFacade) companyFacade).deleteCoupon(6);
		coupons = admin.getAllCoupons();
		PrintUtils.printCouponsTable(coupons);

		PrintUtils.printTestLine("Testing get company coupons - company 2");
		coupons = ((CompanyFacade) companyFacade).getCompanyCoupons();
		PrintUtils.printCouponsTable(coupons);

		companyFacade = loginManager.login("com1@g.com", "com1", ClientType.COMPANY);

		PrintUtils.printTestLine("Testing get company coupons by category ELECTRICTY - company 1");
		coupons = ((CompanyFacade) companyFacade).getCompanyCouponsByCategory(Category.ELECTRICITY);
		PrintUtils.printCouponsTable(coupons);

		PrintUtils.printTestLine("Testing get company coupons Max price 50 - company 1");
		coupons = ((CompanyFacade) companyFacade).getCompanyCouponsByPrice(50);
		PrintUtils.printCouponsTable(coupons);

		PrintUtils.printTestLine("Testing get company details - company 1");
		System.out.println(((CompanyFacade) companyFacade).getCompanyDetails());

	}

}
