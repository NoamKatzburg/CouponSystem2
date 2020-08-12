package com.noam.CouponSystem2.service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
public class CompanyFacade extends ClientFacade {

	private int companyId;

	@Override
	public boolean login(String email, String password) throws SQLException {
		if (companyDBDAO.isCompanyExist(email, password)) {
			setCompanyId(companyDBDAO.getCompanyByEmail(email).getId());
			System.out.println("company id is now: " + companyId);
			return true;
		}
		return false;
	}
	
	public void addCoupon(Coupon coupon) {
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		for (Coupon coup : coupons) {
			if (coup.getCompanyId()==coupon.getCompanyId() && coup.getTitle() == coupon.getTitle()) {
				System.out.println("This company already has a coupon by this title. please rename");
				//TODO exception
				return;
			}
		}
		couponDBDAO.addCoupon(coupon);
	}
	public void updateCoupon(Coupon coupon) {
		couponDBDAO.updateCoupon(coupon);
	}

	public void deleteCoupon(int id) {
		couponDBDAO.deleteCoupon(id);
	}
	
	public List<Coupon> getCompanyCoupons() {
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		Iterator<Coupon> iter = coupons.iterator();

		while (iter.hasNext()) {
			Coupon coup = iter.next();

			if (coup.getCompanyId() != companyId) {
				iter.remove();
			}
		}
		return coupons;
	}
	public List<Coupon> getCompanyCouponsByCategory(Category category){
		List<Coupon> coupons = getCompanyCoupons();
		Iterator<Coupon> iter = coupons.iterator();

		while (iter.hasNext()) {
			Coupon coup = iter.next();

			if (!coup.getCategory().equals(category)) {
				iter.remove();
			}
		}
		return coupons;
	}
	public List<Coupon> getCompanyCouponsByPrice(double maxPrice){
		List<Coupon> coupons = getCompanyCoupons();

		Iterator<Coupon> iter = coupons.iterator();

		while (iter.hasNext()) {
			Coupon coup = iter.next();

			if (coup.getPrice() >= maxPrice) {
				iter.remove();
			}
		}
		return coupons;
	}
	
	public Company getCompanyDetails() {
		return companyDBDAO.getOneCompany(companyId);
		//TODO will coupons be eagerly loaded?
	}
	
}
