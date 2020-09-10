package com.noam.CouponSystem2.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.exception.CouponPurchaseException;
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

	public void purchaseCoupon(Coupon coupon) throws CouponPurchaseException {

		if (getCustomerCoupons().contains(coupon)) {
			throw new CouponPurchaseException(
					"you have already purchased this coupon, coupon: " + couponDBDAO.getOneCoupon(coupon.getId()));
		}

		if (coupon.getAmount() < 1) {
			throw new CouponPurchaseException("This coupon is out of stock");
		}
		if (coupon.getEndDate().before(new Date())) {
			throw new CouponPurchaseException(
					"this coupon has expired, Coupon: " + couponDBDAO.getOneCoupon(coupon.getId()));
		}
		couponDBDAO.addCouponPurchase(customerId, coupon.getId());
		coupon.setAmount(coupon.getAmount() - 1);
		couponDBDAO.updateCoupon(coupon);

	}

	public void deleteCouponPurchase(int customer_id, int coupons_id) {
		couponDBDAO.deleteCouponPurchase(customer_id, coupons_id);
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
		
	}
}
