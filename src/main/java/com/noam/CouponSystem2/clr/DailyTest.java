package com.noam.CouponSystem2.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.dbdao.CouponDBDAO;
@Component
@Order(5)
public class DailyTest implements CommandLineRunner {

	@Autowired
	private CouponDBDAO coupon;
	@Autowired
	
	@Override
	public void run(String... args) throws Exception {
		
		Coupon coupon1 = new Coupon();
		coupon1.setCompanyId(2);
		coupon1.setCategory(Category.VACATION);
		coupon1.setTitle("Daily Test");
		coupon1.setDescription("desc...");
		coupon1.setStartDate(java.sql.Date.valueOf("2020-09-01"));
		coupon1.setEndDate(java.sql.Date.valueOf("2020-09-02"));
		coupon1.setAmount(2000);
		coupon1.setPrice(100);
		coupon1.setImage("img");
		coupon.addCoupon(coupon1);
				
		
		

	}

}
