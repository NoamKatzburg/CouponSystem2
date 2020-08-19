package com.noam.CouponSystem2.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.dbdao.CustomerDBDAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Scope("prototype")
public class CustomerFacade extends ClientFacade {

	private int customerId;

	@Override
	public boolean login(String email, String password) throws SQLException {
		if (customerDBDAO.isCustomerExist(email, password)) {
			setCustomerId(customerDBDAO.getCustomerByEmail(email).getId());
			System.out.println("customer id is now: " + customerId);
			return true;
		}

		return false;
	}

	public void purchaseCoupon(Coupon coupon, int couponId) {
		if (couponDBDAO.doesCouponPurchaseExist(customerId, couponId) != null) {
			System.out.println("you have already purchased this coupon, coupon: " + couponDBDAO.getOneCoupon(couponId));
			return;
			// TODO exception
		}
		if (coupon.getAmount() < 1) {
			System.out.println("This coupon is out of stock");
			return;
			// TODO exception
		}
		if (coupon.getEndDate().before(new Date())) {
			System.out.println("this coupon has expired, Coupon: " + couponDBDAO.getOneCoupon(couponId));
			return;
			// TODO exception
		}
		couponDBDAO.addCouponPurchase(customerId, couponId);

	}

	public List<Coupon> getCustomerCoupons() {
		return customerDBDAO.getOneCustomer(customerId).getCoupons();
	}

	public List<Coupon> getCustomerCouponsByCategory(Category category) {
		List<Coupon> coupons = getCustomerCoupons();
		Iterator<Coupon> iter = coupons.iterator();

		while (iter.hasNext()) {
			Coupon coup = iter.next();

			if (!coup.getCategory().equals(category)) {
				iter.remove();
			}
		}
		return coupons;
	}
	
	public List<Coupon> getAllCouponsByCompanyId(int id) {
		return couponDBDAO.getAllCouponsByCompanyId(id);
	}

	public List<Coupon> getCustomerCouponsByPrice(double maxPrice) {
		List<Coupon> coupons = getCustomerCoupons();

		Iterator<Coupon> iter = coupons.iterator();

		while (iter.hasNext()) {
			Coupon coup = iter.next();

			if (coup.getPrice() >= maxPrice) {
				iter.remove();
			}
		}
		return coupons;
	}

	public Customer getCustomerDetails() {
		return customerDBDAO.getOneCustomer(customerId);
		//TODO do coupon eagerly load?
	}
}
