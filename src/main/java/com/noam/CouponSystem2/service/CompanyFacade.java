package com.noam.CouponSystem2.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
public class CompanyFacade extends ClientFacade {

	private int companyId;

	@Override
	public boolean login(String email, String password) throws SQLException {
		// TODO finish method
		return false;
	}
	
	public void addCoupon(Coupon coupon) {
		
	}
	public void updateCoupon(Coupon coupon, int id) {
		
	}

	public void deleteCoupon(int couponId) {
		
	}
	
	public List<Coupon> getCompanyCoupons() {
		return null;
	}
	public List<Coupon> getCompanyCouponsByCategory(Category category){
		return null;
	}
	public List<Coupon> getCompanyCouponsByPrice(double maxPrice){
		return null;
	}
	
	public Company getCompanyDetails() {
		return null;
	}
	
}
