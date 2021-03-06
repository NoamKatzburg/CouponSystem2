package com.noam.CouponSystem2.service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.exception.InvalidInputException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
@Scope("prototype")
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

	public void addCoupon(Coupon coupon) throws InvalidInputException {
		List<Coupon> coupons = couponDBDAO.getAllCoupons();
		for (Coupon coup : coupons) {
			if (coup.getCompanyId() == coupon.getCompanyId() && coup.getTitle() == coupon.getTitle()) {
				throw new InvalidInputException("This company already has a coupon by this title. please rename");
			}
		}
		couponDBDAO.addCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon) {
		couponDBDAO.updateCoupon(coupon);
	}

	public void deleteCoupon(int id) {
		//Works without as well
// 		Coupon c1 = couponDBDAO.getOneCoupon(id);	
//		List<Customer> customers = customerDBDAO.getAllCustomers();
//		for (Customer customer : customers) {
//			if (customer.getCoupons().contains(c1)) {
//				int c1_id = customer.getCoupons().indexOf(c1);
//				customer.deleteCouponPurchase(c1_id);
//			}
//		}
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

	public List<Coupon> getCompanyCouponsByCategory(Category category) {
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

	public List<Coupon> getCompanyCouponsByPrice(double maxPrice) {
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
		Company c1 = companyDBDAO.getOneCompany(companyId);
		c1.setCoupons(couponDBDAO.getAllCouponsByCompanyId(companyId));
		return c1;
	}

}
